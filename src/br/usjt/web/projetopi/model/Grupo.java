package br.usjt.web.projetopi.model;

import java.util.ArrayList;

public class Grupo{
   int id, numero;
   Professor orientador;
   String nome;
   Turma turma;
   ArrayList<Aluno> alunos;
   
   public Grupo(){
   }
   
   public Grupo(int id, int numero, Professor orientador, String nome){
      this.id = id;
      this.numero = numero;
      this.orientador = orientador;
      this.nome = nome;
   }
   
   public int getId(){
      return id;
   }
   
   public int getNumero(){
      return numero;
   }
   
   public Professor getOrientador(){
      return orientador;
   }
   
   public String getNome(){
      return nome;
   }
   public Turma getTurma()
   {
	   return turma;
   }
   public ArrayList<Aluno> getAlunos()
   {
	   return alunos;
   }
   //Setters
   public void setId(int id){
      this.id = id;
   }
   
   public void setNumero(int numero){
      this.numero = numero;
   }
   
   public void setOrientador(Professor orientador){
      this.orientador = orientador;
   }
   
   public void setNome(String nome){
      this.nome = nome;
   }
   public void setTurma(Turma turma)
   {
	   this.turma = turma;
   }
   public void setAlunos(ArrayList<Aluno> alunos)
   {
	   this.alunos = alunos;
   }
}