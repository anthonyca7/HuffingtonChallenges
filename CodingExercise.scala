// Anthony Cabrera
// anthonyka7@gmail.com
// (347) 772-5414


// I have written more Scala code using akka which I am willing to show on request. I am ready to use Scala
// for any appropiate task

import scala.math.abs
import scala.collection.mutable.HashMap


//   a) Given a sorted list of distinct integers from 0 to 99, for instance [0, 1, 2, 50, 52, 75]. Write code to
//   produce a string that describes numbers missing from the list; in this case "3­-49,51,53­-74,76­-99".
def gap(a:Int, b:Int):String = {
	val diff = b - a

	if (diff == 2) (b-1).toString
	else if (diff > 2)  (a+1).toString + "-" + (b-1).toString
	else "" // We never get here in this script
}

// The List of numbers is sorted and the values range from 0-99
def missingNumbersList(numbers:List[Int]) = {
	for {
		(a, b) <- numbers zip (numbers.tail :+ 100)
		if b-a > 1
	} yield gap(a, b)
}

def missingNumbers(numbers:List[Int]) = {
	val missing = missingNumbersList(numbers) 
	missingNumbersList(numbers).mkString(",")
}

// Simple test
println("Test for Part A")
println(missingNumbers(List(0, 1, 2, 50, 52, 75)))
println()

//   b) Find the longest consecutive range in an array, e.g. {5,6,9,8,7,2,1,11}
case class Range(var occurances:Int, var value:Int, var start:Int, var end:Int)

def maxConsecutiveRange(numbers:Array[Int]) = {
	if (numbers.length < 2) None // We need at least 2 numbers to get a range
	else {
		val sorted = numbers.sorted
		val intervals = sorted.init zip sorted.tail
		val ranges = intervals map ((x:(Int, Int)) => abs(x._1 - x._2))

		var consecutiveRanges = Array(Range(1, ranges(0), 0, 0))
		var i = 1

		while (i < ranges.length) {
			var last = consecutiveRanges.last
			val range = ranges(i) 

			if (range == last.value) {
				last.occurances += 1
				last.end = i
			}
			else {
				consecutiveRanges :+= Range(1, range, i, i)
			}

			i += 1
		}

		val maxRange = consecutiveRanges reduce ((x:Range, y:Range) => if (x.occurances > y.occurances) x else y)
		intervals(maxRange.start)._1.toString + "-" + intervals(maxRange.end)._2.toString 
	}
}

// Simple tests
println("Test for Part B")
println(maxConsecutiveRange(Array(5,6,9,8,7,2,1,11)))
println(maxConsecutiveRange(Array(5,4,9,8,7,2,10,11)))
println(maxConsecutiveRange(Array(5,11)))
println(maxConsecutiveRange(Array(5)))
