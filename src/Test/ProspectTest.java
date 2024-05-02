/*
package Test;

import Entities.Prospect;

import MetierException.MetierException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProspectTest extends Prospect {

    private Prospect prospect;

    @BeforeEach
    void setUp() {
        // Initialisation du prospect (vous pouvez personnaliser les valeurs ici)
        prospect = new Prospect();
    }
*/
/*
*
     * Test unitaire pour la méthode setDateProspection de la classe Prospect.
     * Vérifie le comportement de la méthode setDateProspection lorsque des valeurs de date de prospection valides et nulles sont utilisées.

*//*



    @Test
    void testSetDateProspectionWithValidValue() throws  MetierException {
        // Test avec une date de prospection valide
        LocalDate validDateProspection = LocalDate.of(2024, 3, 10);
        prospect.setDate_prospection(validDateProspection);
        // Test avec une date de prospection nulle
        assertThrows(MetierException.class, () -> prospect.setDate_prospection(null));

    }


}
*/
