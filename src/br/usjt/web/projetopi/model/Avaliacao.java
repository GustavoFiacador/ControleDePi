package br.usjt.web.projetopi.model;
public class Avaliacao{
   int id;
   double nota;
   String dtAvaliacao, comentarios;
   Entrega entrega;
   Aluno aluno;
   
   public Avaliacao(){
   }
   
   public Avaliacao(int id, Aluno aluno, double nota, String dtAvaliacao, String comentarios){
      this.id = id;
      this.aluno = aluno;
      this.nota = nota;
      this.dtAvaliacao = dtAvaliacao;
      this.comentarios = comentarios;
   }
   
   public int getId(){
      return id;
   }
   
public Aluno getAluno() {
	return aluno;
}

public Entrega getEntrega() {
	return entrega;
}

public void setEntrega(Entrega entrega) {
	this.entrega = entrega;
}

public void setAluno(Aluno aluno) {
	this.aluno = aluno;
}

public double getNota(){
      return nota;
   }
   
   public String getDtAvaliacao(){
      return dtAvaliacao;
   }
   
   public String getComentarios(){
      return comentarios;
   }
   
   //Setters
   public void setId(int id){
      this.id = id;
   }
   
   public void setNota(double nota){
      this.nota = nota;
   }
   
   public void setDtAvaliacao(String dtAvaliacao){
      this.dtAvaliacao = dtAvaliacao;
   }
   
   public void setComentarios(String comentarios){
      this.comentarios = comentarios;
   }
}
