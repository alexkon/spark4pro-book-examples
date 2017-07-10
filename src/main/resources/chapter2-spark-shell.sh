#!/usr/bin/env bash

# create test RDD array

val rawblocks = sc.textFile("linkage")
val head = rawblocks.take(10)
head.foreach(println)
def isHeader(line: String) = line.contains("id_1")
head.filter(isHeader).foreach(println)


# match RDD to case class

val noheader = rawblocks.filter(!isHeader(_))

def toDouble(s: String) = if ("?".equals(s)) Double.NaN else s.toDouble

case class MatchData(id1: Int, id2: Int, scores: Array[Double], matched: Boolean)

def parse(line: String) = {
    val pieces = line.split(',')
    val id1 = pieces(0).toInt
    val id2 = pieces(1).toInt
    val scores = pieces.slice(2,11).map(toDouble)
    val matched = pieces(11).toBoolean
    MatchData(id1, id2, scores, matched)
}

val parsed = noheader.map(parse(_))
val matchCounts = parsed.map(md => md.matched).countByValue()
val matchCountsSeq = matchCounts.toSeq
matchCountsSeq.sortBy(_._2).reverse.foreach(println)