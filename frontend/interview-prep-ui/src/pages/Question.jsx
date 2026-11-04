import { useState } from 'react'

function Questions() {

    const [questions, setQuestions] = useState([])
    const [answers, setAnswers] = useState({})

    const token = localStorage.getItem('token')

    const getQuestions = async () => {

        const response = await fetch(
            'http://localhost:8081/students/questions',
            {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            }
        )

        const data = await response.json()

        setQuestions(data)
    }

    return (
        <div>

            <h1>Questions</h1>

            <button onClick={getQuestions}>
                Get Questions
            </button>

            {questions.map((question) => (
                <div key={question.id}>

                    <h3>{question.questions}</h3>

                    <button onClick={() => setAnswers({...answers, [question.id]: "A"})}>
                        A. {question.optionA}
                    </button>

                    <button onClick={() => setAnswers({...answers, [question.id]: "B"})}>
                        B. {question.optionB}
                    </button>

                    <button onClick={() => setAnswers({...answers, [question.id]: "C"})}>
                        C. {question.optionC}
                    </button>

                    <button onClick={() => setAnswers({...answers, [question.id]: "D"})}>
                        D. {question.optionD}
                    </button>

                    <p>
                        Selected: {answers[question.id]}
                    </p>

                    <hr />

                </div>
            ))}

        </div>
    )
}

export default Questions