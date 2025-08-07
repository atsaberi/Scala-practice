package practice.dataengineering

import practice.{CountDistinctEventsHr, Event}

import java.time.format.DateTimeParseException
import java.time.{Instant, ZoneOffset}
import scala.io.Source
import scala.util.{Failure, Success, Try}
/******
 * Metric: Number of unique events per hour
 *
 *
 * 1.Open and read lines from the log file
 * 2.Parse the log file
 * 3.Calculate the metric
 */

case class Event(createdTs: Instant, eventType: String , userId:String)
case class CountDistinctEventsHr(createdHr: Integer,countEventsMetric: Integer)


class EventsMetric {

  val filename = "event-logs.txt"

  /**
   * A generic readTextFile to read a file using Source and apply parseLineToEvent 
   * 
   * @param f   parseLineToEvent 
   * @tparam T  type of the parse data
   * @return   data generic type
   */
  def readTextFile[T](f: Iterator[String] => T): Option[T] = {
    Try {
      val source: Source = Source.fromFile(filename)
      try f(source.getLines())
      finally source.close()
    } match {
      case Success(data) =>
        println(s"File content: \n$data")
        Some(data)
      case Failure(e: java.io.FileNotFoundException) =>
        println(s"file: $filename not found: ${e.getMessage}")
        None
      case Failure(e: java.io.IOException) =>
        println(s"IO Exception: ${e.getMessage}")
        None
      case Failure(e) =>
        println(s"Unexpected error: ${e.getClass.getSimpleName}: ${e.getMessage}")
        None
    }
  }

  /**
   *  function to parse the file and return the specific data type 
   *  in this case: Event createdTs: Instant, eventType: String , userId:String
   *
   * @param line String 
   * @return Event 
   */
  def parseLineToEvent(line: String):Option[Event] = {
    val attributes = line.split(" ")

    if (attributes.length == 3)
      try {
        Some(Event(Instant.parse(attributes(0)), attributes(1), attributes(2)))
      } catch {
        case e:DateTimeParseException =>
          println(s"${e.getMessage}")
          None
      }
    else None

  }

  /**
   * Extracting hour from Instant date time type
   * 
   * @param instant
   * @return
   */
  def extractHourFromInstantUTC(instant: Instant): Int = {
    val zdt = instant.atZone(ZoneOffset.UTC)
    zdt.getHour
  }

  /**
   * Transformation to calculate "number of unique events per hour" metric
   * @param events
   * @return
   */
  def eventsMetricTransformer(events: Option[List[Event]]): List[CountDistinctEventsHr] = {
    events match {
      case Some(events) =>
        val hourlyGrouped = events.groupBy(e => extractHourFromInstantUTC(e.createdTs))
        val hourlyCount: Map[Int, Int] = hourlyGrouped.map { case (hour, events) => (hour, events.map(_.eventType).distinct.size)}
        hourlyCount.map { case (hour, nbEvents) => CountDistinctEventsHr(hour, nbEvents) }.toList

      case None =>
        List.empty
    }
  }

  def main() = {
    val events: Option[List[Event]] = readTextFile { (lines: Iterator[String]) =>
      lines.flatMap(parseLineToEvent).toList
    }

    events.match {
      case Some(events: List[Event]) => {
        val eventsMetric: List[CountDistinctEventsHr] = eventsMetricTransformer(Some(events: List[Event]))
        println(s"Event Metric number of hourly distinct events: $eventsMetric")
      }
      case None => println("Skipping processing due to file read failure.")
    }
  }
}


