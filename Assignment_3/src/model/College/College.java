/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.College;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import model.Department.Department;
import model.Persona.Account;
import model.Persona.AccountDirectory;
import model.Persona.Faculty.FacultyDirectory;
import model.Persona.PersonDirectory;

/**
 *
 * @author aeloyq
 */
public class College {

    String name;
    Account account;
    PersonDirectory personDirectory = PersonDirectory.getInstance();
    AccountDirectory accountDirectory = AccountDirectory.getInstance();
    HashMap<String, Department> departments = new HashMap<String, Department>();

    public College(String name) {
        this.name = name;
        try {
            CollegeProfile cp = new CollegeProfile();
            account = accountDirectory.newAccount(name.split(" ")[2], "admin", cp);
            cp.setAccount(account);
        } catch (AccountDirectory.AccountNameConflictException ex) {
        }
    }

    public Department newDepartment(String name, String code) {
        Department department = new Department(name, code, this);
        departments.put(name, department);
        return department;
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
    }

    public Department getDepartment(String name) {
        return departments.get(name);
    }

    public HashMap<String, Department> getDepartments() {
        return departments;
    }

    public String getName() {
        return name;
    }
}
