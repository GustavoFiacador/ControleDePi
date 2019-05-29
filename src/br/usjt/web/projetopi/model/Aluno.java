package br.usjt.web.projetopi.model;
public class Aluno extends Usuario{
   int ra;
   Turma turma;
   
   public Aluno(){
	   super();
   }
   
   public Aluno(int id, String nome, String email, String senha, int ra){
      super(id, nome, email, senha);
      this.ra = ra;
   }
   
   public int getRa(){
      return ra;
   }
   public Turma getTurma()
   {
	   return turma;
   }
   
   //Setters
   public void setRa(int ra){
      this.ra = ra;
   }
   public void setTurma(Turma turma)
   {
	   this.turma = turma;
   }
}
