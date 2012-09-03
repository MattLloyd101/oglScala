package com.mattlloyd.opengl.data.resources



object ResourceImplicits {
    implicit def String2Resource[T](value : String):Resource[T] = ResourceManager.getResourceByIdSafe(value).get.asInstanceOf[Resource[T]]
}
object ResourceManager {

    type RHS =  Either[Bundle, Resource[_]]
    class Bundle(map: Map[String, RHS] = Map[String, RHS]()) extends _Bundle[RHS](map)

    class _Bundle[T >: RHS](map: Map[String, T] = Map[String, T]()) extends Map[String, T] {
        def get(key: String) = map.get(key)
        def -(key: String) = map.-(key)
        def iterator: Iterator[(String, T)] = map.iterator
        def +[B1 >: T](kv: (String, B1)) = map + kv
    }

    var resources:Bundle = new Bundle()

    def getBundleById[T](id:String):Option[Bundle] = getById[T](id) match {
        case Some(Left(resource)) => Some(resource)
        case _ => None
    }

    def getResourceById[T](id:String) = getResourceByIdSafe[T](id).get

    def getResourceByIdSafe[T](id:String) = getById[T](id) match {
        case Some(Right(resource)) => Some(resource)
        case _ => None
    }

    def pathFromId(id:String):List[String] = id.split("/").toList

    def get[T](id:String):Option[Either[Bundle, Resource[T]]] = getById[T](id)

    // locks on resources once found.
    def getById[T](id:String):Option[Either[Bundle, Resource[T]]] = getByPath(pathFromId(id))

    def getByPath[T](path:Seq[String]):Option[Either[Bundle, Resource[T]]] = {
        path.foldLeft(Some(Left(resources)):Option[Either[Bundle, Resource[T]]]) { (resources, currPath) =>
            resources match {
                // traverse down
                case Some(Left(bundle)) => bundle.get(currPath).asInstanceOf[Option[Either[Bundle,Resource[T]]]]
                // found resource
                case option@Some(Right(_)) => option
                case _ => None
            }
        }
    }

    def +=(resourceSet:ResourceBundle) = put(resourceSet)

    def +=(resource:Resource[_]) = put(resource)

    def put(resourceSet:ResourceBundle) { resourceSet.resources foreach put }

    def put(resource:Resource[_]):Bundle = {
        resources = put(pathFromId(resource.id), resource, resources)
        resources
    }

    def put(path:Seq[String], data:Resource[_], bundle:Bundle):Bundle = path match {
        case id :: Nil => new Bundle(bundle + (id -> Right(data)))

        case pathPart :: newPath => {
            new Bundle(bundle + (pathPart -> Left(bundle.get(pathPart) match {
                case Some(Left(bundle)) => put(newPath, data, bundle)
                case Some(Right(_)) => throw new Error("Resource already exists at "+ path)
                case None => put(newPath, data, new Bundle())
            })))
        }
    }

}
