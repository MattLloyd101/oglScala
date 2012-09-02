package examples.shader.tess

import io.Source
import java.util.Date

case class DataCls(ranking:Map[String, Int], w:Int, h:Int, grid:List[List[String]], words:Set[String], closedSet:Set[(Int, Int)], currWord:String = "") {
    def update(pos:(Int, Int), newWord:String) = DataCls(ranking, w, h, grid, words, closedSet + pos, newWord)
}

object Main {

    def loadGrid(str:String):(List[List[String]], Int, Int) = {
        str.toLowerCase.split("\r\n").foldLeft((List[List[String]](), 0, 0)) { (out, str) =>
            val (row, len) = str.split(" ").foldLeft((List[String](), 0)) { (out, chr) =>
                (out._1 :+ chr, out._2 + 1)
            }
            (out._1 :+ row, len, out._3 + 1)
        }
    }

    def timed[T](fn: => T) = {
        val t = new Date().getTime
        (fn, new Date().getTime - t)
    }

    def loadWords = Source.fromURL("http://dotnetperls-controls.googlecode.com/files/enable1.txt").getLines.map { _.toLowerCase }.toSet

    def wordParts(word:String, i:Int = 1):List[String] = {
        if(i == word.length)
            List[String](word)
        else
            word.substring(0, i) :: wordParts(word, i+1)
    }

    def buildRanking(map:Map[String, Int], word:String) = {
        wordParts(word).foldLeft(map) { (map, wordPart) =>
            map + (map.get(wordPart) match {
                case Some(score) => (wordPart, score + word.length)
                case None => (wordPart, word.length)
            })
        }
    }

    def inBounds(w:Int, h:Int)(x:Int, y:Int) = x >= 0 && y >= 0 && x < w && y < h

    def getNeighbours(data:DataCls, pos:(Int, Int)):Seq[((Int, Int), String)] = {
        val (dx, dy) = pos
        (for (x <- -1 to 1;
              y <- -1 to 1) yield (x+dx, y+dy))
            .filterNot { data.closedSet.contains(_) }
            // TODO: this should be handled by closed set test.
            .filterNot { _ == pos}
            .filter { pos => inBounds(data.w, data.h)(pos._1, pos._2) }
            .map { pos:(Int, Int) => (pos, data.currWord + data.grid(pos._1)(pos._2)) }
    }

    def evaluatePosition(data:DataCls, pos:(Int, Int)):List[String] = getNeighbours(data, pos)
        .filter { n => data.ranking.contains(n._2) }
        //.sortBy { n => data.ranking.getOrElse(n._2, 0) }
        .foldLeft(List[String]()) { (out, tup) =>
            val (n, word) = tup
            if(data.words.contains(word))
                word :: out ++ evaluatePosition(data.update(pos, word), n)
            else
                out ++ evaluatePosition(data.update(pos, word), n)
        }


    def evaluateGrid(grid:List[List[String]], w:Int, h:Int):Seq[String] = {
        println("loading words")
        val words = loadWords

        println("building ranking map")
        val ranking = words.foldLeft(Map[String, Int]())(buildRanking)

        println("evaluating characters")
        (for (x <- 0 until w;
              y <- 0 until h)
        yield evaluatePosition(DataCls(ranking, w, h, grid, words, Set[(Int, Int)]((x, y))), (x, y))).flatten
    }

    def main(args: Array[String]) {

        val matrix = """T N L E P I A C N M
T R E H O C F E I W
S C E R E D A M E A
A O M O G O O F I E
G A C M H N L E R X
T D E R J T F O A S
I T A R T I N H N T
R N L Y I V S C R T
A X E P S S H A C F
I U I I I A I W T T"""

        println("loading grid")
        val (grid, w, h) = loadGrid(matrix)

        println("evaluating grid")
        val (foundWords, time) = timed {
            evaluateGrid(grid, w, h)
        }

        val uniqueWords = foundWords.toSet
        println("Took > " + time + "ms")

        println("Word count: "+uniqueWords.size)
        println("")
        val map = uniqueWords.groupBy { _.length }

        map.toList.sortBy { _._1 } foreach { tup =>
            val (len, words) = tup
            println(len + " letter words: " + words.size)
        }

        println("")
        println("11 words> "+ map(11))
        println("10 words> "+ map(10))
        println(" 9 words> "+ map(9))
    }

}
