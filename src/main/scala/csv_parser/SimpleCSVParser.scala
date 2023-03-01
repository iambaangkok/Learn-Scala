package csv_parser

import scala.util.matching.Regex

object SimpleCSVParser {
  private val alphaNumeric: Regex = "[0-9a-zA-Z- ]".r
  private val pattern: Regex = s"(${alphaNumeric}*),(${alphaNumeric}+),(${alphaNumeric}+),(${alphaNumeric}+),(${alphaNumeric}+)".r

  def parse(input: String, pattern: Regex = SimpleCSVParser.pattern, omitFirstLine : Boolean = true): List[List[String]] = {

    println(pattern)

    def getList(matches : Iterator[Regex.Match], dataFrame : List[List[String]]): List[List[String]] = {
      println(dataFrame.mkString)
      if( matches.hasNext ){
        val patternMatch = matches.next()
        val row: List[String] = List(patternMatch.group(1), patternMatch.group(2), patternMatch.group(3), patternMatch.group(4), patternMatch.group(5))

        row :: getList(matches.drop(1), dataFrame)
      }else{
        dataFrame
      }
    }

    if(omitFirstLine){
      getList(pattern.findAllMatchIn(input).drop(1), List())
    }else{
      getList(pattern.findAllMatchIn(input), List())
    }
  }
}