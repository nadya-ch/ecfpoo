package Test;

import Entities.Client;
import MetierException.MetierException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest extends Client {

    private Client client;

    @BeforeEach
    void setUp() {
        // Initialisation du client (vous pouvez personnaliser les valeurs ici)
        client = new Client();
    }
    /**
     * Test unitaire pour la méthode setChiffreAffaires de la classe Client.
     * Vérifie le comportement de la méthode setChiffreAffaires lorsque des valeurs de chiffre d'affaires valides et invalides sont utilisées.
     */

    @Test
    void testSetChiffreAffaires() throws  MetierException {
        // Test avec un chiffre d'affaires valide
        double validChiffreAffaires = 300.0;
        client.setChiffre_affaires(validChiffreAffaires);


        // Test avec un chiffre d'affaires inférieur à 200
        double invalidChiffreAffaires = 150.0;
        assertThrows(MetierException.class, () -> client.setChiffre_affaires(invalidChiffreAffaires));
    }
    /**
     * Test unitaire pour la méthode setNbEmployes de la classe Client.
     * Vérifie le comportement de la méthode setNbEmployes lorsque des valeurs de nombre d'employés valides et invalides sont utilisées.
     */

    @Test
    void testSetNbEmployesWithValidValue() throws MetierException {
        // Test avec un nombre d'employés valide
        int validNbEmployes = 10;
        client.setNb_employes(validNbEmployes);

        int invalidNbEmployes = -5;
        assertThrows(MetierException.class, () -> client.setNb_employes(invalidNbEmployes));

    }


}