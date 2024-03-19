package com.school.management.system;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String [] args) {
		
		Teacher lizzy = new Teacher(1, "Lizzay",5000);
		Teacher mellisa = new Teacher(2,"Mellisa",700);
		Teacher vanderhorn = new Teacher(3, "Vanderhorn" ,600);
		
		List<Teacher> teacherlist = new ArrayList<>();
		teacherlist.add(lizzy);
		teacherlist.add(mellisa);
		teacherlist.add(vanderhorn);
		
		Student tamasha = new Student(1, "Tamasha", 4);
		Student rakshith = new Student(2,"rakshith", 12);
		Student rabbi = new Student(3,"Rabbi",5);
		
		List<Student>studentlist = new ArrayList<>();
		studentlist.add(tamasha);
		studentlist.add(rabbi);
		studentlist.add(rakshith);
		
		School ghs = new School(teacherlist, studentlist);
		
		Teacher megan = new Teacher(6,"Megan",900);
		
		ghs.addTeachers(megan);
		
		tamasha.payFees(5000);
		rakshith.payFees(6000);
		System.out.println("GHS has  earned $" + ghs.getTotalMoneyEarned());
		
		System.out.println("-----------Making SCHOOL PAY SALARY----------");
		lizzy.receiveSalary(lizzy.getSalary());
		System.out.println("GHS has spent for salary to " + lizzy.getName() + "and now has $" + ghs.getTotalMoneyEarned());
		
		vanderhorn.receiveSalary(vanderhorn.getSalary());
		System.out.println("GHS has spent for salary to " + vanderhorn.getName() + "and now has $" + ghs.getTotalMoneyEarned());
		
		System.out.println(rakshith);
		
		mellisa.receiveSalary(mellisa.getSalary());
		
		System.out.println(tamasha);
	}

}
