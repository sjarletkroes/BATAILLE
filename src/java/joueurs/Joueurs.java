/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joueurs;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author me
 */
@XmlRootElement(name = "Joueurs")
public class Joueurs {
    @XmlElement
    public ArrayList<JoueurImpl> liste = new ArrayList<>();
}
