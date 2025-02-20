import java.util.*;
class QuizApplication{
  public static class QuizQuestion{
    String question;
    String[] options;
    int correctOption;

    QuizQuestion(String question,String[] options,int correctOption){
      this.question=question;
      this.options=options;
      this.correctOption=correctOption;
    }    
}
private static final Scanner sc=new Scanner(System.in);
private static int score=0;
private static final int TIME_LIMIT=6;


 private static final QuizQuestion[] questions= new  QuizQuestion[] {
  new QuizQuestion("What is the capital of France?",new String[]{"Rome","Madrid","France","Berlin"},3),
 new QuizQuestion("Which programming language is used for Android Development?",new String[]{"Java","Python","C++","Ruby"},1)
};

private static void runQuiz(){
  for(QuizQuestion q:questions){
     System.out.println(q.question);
     for(int i=0;i<q.options.length;i++){
      System.out.println((i+1)+"."+q.options[i]);
     }
  
  int userAnswer=getUserInputWithTimer();
  if(userAnswer==-1){
      System.out.println("Time's Up!!Moving to next question");
  }
  else if(userAnswer==q.correctOption){
    System.out.println("CORRECT :)");
    score++;
  }
  else{
    System.out.println("INCORRECT :(.The correct answer is "+ q.correctOption);
  }
 
}     
  
}
private static int getUserInputWithTimer(){
final int[] answer={-1};
Thread inputThread=new Thread(()->{
  try {
     System.out.println("Enter your answer(1-4):"); 
     if(sc.hasNextInt()){
      answer[0]=sc.nextInt();
     }
  } catch (Exception ignored) {
  }
});
inputThread.start();
try{
  inputThread.join(TIME_LIMIT*1000);
  
}catch(InterruptedException  e){
 e.printStackTrace();
}
if(inputThread.isAlive()){
  inputThread.interrupt();
 
}
return answer[0];
}
private static void displayResults() {
  System.out.println("\nQuiz Over!");
  System.out.println("Your Final Score: " + score);
}
public static void main(String[] args) {
  runQuiz();
  displayResults();
}


}
