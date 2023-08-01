package com.izzzya.fortunewheel


data class Question(
    var question: String,
    var answerIndex: Int,
    var listAnswers: List<String>
)
class QuestionsSource {
    companion object{


    val questionsList = listOf<Question>(
        Question(
            "What was the old name for a Snickers bar before it changed in 1990?",
            0,
            listOf("Marathon", "Flash", "Sprint")
        ),
        Question(
            "Which popular video game franchise has released games with the subtitles World At War and Black Ops?",
            1,
            listOf("Far Cry", "Call of Duty", "Total War")
        ),
        Question(
            "What is the name of the main antagonist in the Shakespeare play Othello?",
            2,
            listOf("Tin", "William", "Mark")
        ),
        Question(
            "What is the capital of New Zealand?",
            0,
            listOf("Wellington", "Bristol", "Auckland")
        ),
        Question(
            "What band was Harry Styles in before his solo career?",
            1,
            listOf("Big Time Rush", "One Direction", "BTS")
        ),

    )}
}