/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ganbare.domain;

/**
 *
 * @author palolaur
 */
public class User {

    private String name;

    public User(String name) {
        this.name = name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(String name) {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
