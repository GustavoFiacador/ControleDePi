package br.usjt.web.projetopi.model;


public class Professor extends Usuario {
   boolean administrador;
   String matricula;

   
   public Professor() {
      super();
   }
   
   public Professor(int id, String nome, String email, String senha, String matricula, boolean administrador) {
      super (id, nome, email, senha);
      this.matricula = matricula;
      this.administrador = administrador;
   }
   
   public String getMatricula() {
      return matricula;
   }
   
   public boolean getAdministrador() {
      return administrador;
   }
   

   
   //Setters
   public void setMatricula(String matricula) {
      this.matricula = matricula;
   }
   
   public void setAdministrador(Boolean administrador) {
      this.administrador = administrador;
   }
   

}
