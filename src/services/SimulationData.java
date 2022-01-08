package services;

import modeles.Caissier;
import modeles.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe avec les variables de la simulation en statique afin de les garder tout le temps de la siulation totalement accessible
 */
public class SimulationData {

    private static List<Caissier> caissiers = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();
    public static int dureeSimulation = 0;
    public static int tempsMoyenEntreDeuxArrives = 0;
    public static int tempsEcoule = 0;
    public static int longueurMaximaleFileDattente = 0;
    public static double longueurMoyenneFileDattente = 0;

    public static void addCaissier(Caissier c) {
        caissiers.add(c);
    }

    public static List<Caissier> getCaissiers() {
        return caissiers;
    }

    public static List<Client> getClients() {
        return clients;
    }

    /**
     * ajoute un client dans la liste
     * Si un caissier est disponible il le lui affilie
     * Sinon il rentre dans la file d'attente automatiquement
     *
     * @param client
     * @param t
     * @return
     */
    public static boolean addClient(Client client, int t) {
        clients.add(client);
        for (Caissier c : caissiers) {
            if (c.getClientActuel() == null) {
                c.servirClient(client, t);
                client.setHeureDebutService(t);
                System.out.println("CLIENT " + client.getId() + " ATTRIBUE A CAISSIER " + c.getId() + " PENDANT INITIALISATION");
                return true;
            }
        }
        return false;
    }

    /**
     * si un caissier est disponible on lui envoie un des clients en attente
     *
     * @param t
     * @return
     */
    public static boolean traiterFileDattente(int t) {
        boolean changes = false;
        for (Client c : clients) {
            if (c.getHeureDebutService() == 0 && !c.isEstServi()) {
                for (Caissier ca : caissiers) {
                    if (ca.getClientActuel() == null) {
                        ca.servirClient(c, t);
                        c.setHeureDebutService(t);
                        changes = true;
                        break;
                    }
                }
            }
        }
        return changes;
    }

    /**
     * permet de vérifier si un caissier a fini de servir sin client actuel
     * le calcul se base sur l'heure de debut du service et le temps moyen de service du caissier
     *
     * @param t
     * @return
     */
    public static boolean verifierFinService(int t) {
        boolean changes = false;
        for (Caissier c : caissiers) {
            if (c.getClientActuel() != null) {
                if (c.getHeureDebutServiceClient() + (c.getTempsMoyenService() * 60) <= t) {
                    finirServiceClient(c.getClientActuel().getId(), t);
                    c.finirServiceClient();
                    changes = true;
                }
            }
        }
        return changes;
    }

    /**
     * permet de stipuler un client comme ayant été servi et le sort de la file d'attente
     *
     * @param idClient
     * @param t
     */
    private static void finirServiceClient(int idClient, int t) {
        for (Client cl : clients) {
            if (cl.getId() == idClient) {
                cl.setEstServi(true);
                cl.setHeureSortie(t);
                break;
            }
        }
    }

    /**
     * permet de récupérer le nombre de caissiers dispo
     *
     * @return
     */
    public static int getNbCaissiersDispo() {
        int cpt = 0;
        for (Caissier c : caissiers) {
            if (c.getClientActuel() == null) cpt++;
        }
        return cpt;
    }

    /**
     * retourne le nombre de caissiers dispo / le nombre total de caissiers
     *
     * @return
     */
    public static String getStrCaissiersDispo() {
        return getNbCaissiersDispo() + " / " + caissiers.size();
    }

    /**
     * retourne le nombre total de clients servis
     *
     * @return
     */
    public static int getNbClientsServi() {
        int cpt = 0;
        for (Client c : clients) {
            if (c.getHeureSortie() != 0) cpt++;
        }
        return cpt;
    }

    /**
     * retourne le nombre de clients en train d'etre servis
     *
     * @return
     */
    public static int getNbClientsEnService() {
        return clients.size() - (getNbClientsEnAttente() + getNbClientsServi());
    }

    /**
     * retourne le nombre de clients en attente
     *
     * @return
     */
    public static int getNbClientsEnAttente() {
        int cpt = 0;
        for (Client c : clients) {
            if (c.getHeureDebutService() == 0) cpt++;
        }
        if (cpt > longueurMaximaleFileDattente)
            longueurMaximaleFileDattente = cpt;
        return cpt;
    }

    /**
     * permet de calculer la moyenne de nombre de clients ans la file d'attente
     *
     * @param nbClientFileDattente liste des changements de la file d'attente
     */
    public static void calculerMoyenneFileDattente(List<Integer> nbClientFileDattente) {
        double somme = 0;
        for (int i : nbClientFileDattente)
            somme += i;
        if (somme > 0)
            longueurMoyenneFileDattente = somme / nbClientFileDattente.size();
    }

    /**
     * retourne le temps moyen d'attente d'un client dans la file d'attente
     *
     * @return
     */
    public static int getTempsMoyenAttente() {
        int somme = 0;
        for (Client c : clients)
            somme += (c.getHeureDebutService() - c.getHeureArrivee());
        return somme / clients.size();
    }

    /**
     * calucle et retourne le temps total d'occupation de tous les caissiers
     *
     * @return
     */
    public static double getTempsTotalOccupation() {
        double somme = 0;
        for (Caissier c : caissiers)
            somme += (c.getTempsOccupation());
        return somme;
    }
}
