/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parties;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Classe d'encapsulation de la liste des parties
 * 
 */
@XmlRootElement(name = "Parties")
public class Parties implements Serializable {
    public Parties() {liste = new ArrayList<>();}
    @XmlElement
    public ArrayList<Partie> liste;
}
