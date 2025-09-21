package za.ac.cput.domain;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;
    private String studentName;
    private String quizTitle;
    private Long quizId;

    @ElementCollection
    private List<String> answers;


    private Integer mark;
    private String feedback;

    // Constructors
    public QuizAttempt() {}
    public QuizAttempt(String studentId, String studentName, String quizTitle, Long quizId, List<String> answers, Integer mark, String feedback) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.quizTitle = quizTitle;
        this.quizId = quizId;
        this.answers = answers;
        this.mark = mark;
        this.feedback = feedback;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getQuizTitle() { return quizTitle; }
    public void setQuizTitle(String quizTitle) { this.quizTitle = quizTitle; }
    public Long getQuizId() { return quizId; }
    public void setQuizId(Long quizId) { this.quizId = quizId; }
    public List<String> getAnswers() { return answers; }
    public void setAnswers(List<String> answers) { this.answers = answers; }

    public Integer getMark() { return mark; }
    public void setMark(Integer mark) { this.mark = mark; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
