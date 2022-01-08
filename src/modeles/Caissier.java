package modeles;

import services.SimulationData;

public class Caissier {

    public static int nbCaissier = 0;

    private int id;
    private int tempsMoyenService;
    private int tempsOccupation;
    private int nbCientServi;
    private int heureDebutServiceClient;
    private Client clientActuel;

    public Caissier(int tempsMoyenService) {
        Caissier.nbCaissier++;
        this.id = nbCaissier;
        this.tempsMoyenService = tempsMoyenService;
        this.clientActuel = null;
        this.tempsOccupation = 0;
        this.nbCientServi = 0;
        this.heureDebutServiceClient = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempsMoyenService() {
        return tempsMoyenService;
    }

    public void setTempsMoyenService(int tempsMoyenService) {
        this.tempsMoyenService = tempsMoyenService;
    }

    public int getTempsOccupation() {
        return tempsOccupation;
    }

    public void setTempsOccupation(int tempsOccupation) {
        this.tempsOccupation = tempsOccupation;
    }

    public int getNbCientServi() {
        return nbCientServi;
    }

    public void setNbCientServi(int nbCientServi) {
        this.nbCientServi = nbCientServi;
    }

    public static int getNbCaissier() {
        return nbCaissier;
    }

    public static void setNbCaissier(int nbCaissier) {
        Caissier.nbCaissier = nbCaissier;
    }

    public int getHeureDebutServiceClient() {
        return heureDebutServiceClient;
    }

    public void setHeureDebutServiceClient(int heureDebutServiceClient) {
        this.heureDebutServiceClient = heureDebutServiceClient;
    }

    public Client getClientActuel() {
        return clientActuel;
    }

    public void setClientActuel(Client clientActuel) {
        this.clientActuel = clientActuel;
    }

    /**
     * permet d'initialiser le service de son nouveau client
     * @param cl : le client à servir
     * @param t  : le temps écoulé pour savoir quand a-t-il commencé à servir le client afin de determine quand finira t-il
     */
    public void servirClient(Client cl, int t) {
        this.clientActuel = cl;
        this.heureDebutServiceClient = t;
    }

    /**
     * permet de finir de servir un client
     * il incremente le nombre de clients servis
     * il augmente le temps d'occupation en y ajoutant le temps de son dernier service
     */
    public void finirServiceClient() {
        this.nbCientServi++;
        this.clientActuel = null;
        this.heureDebutServiceClient = 0;
        this.tempsOccupation += this.tempsMoyenService*60;
    }

    /**
     * permet de calculer le taux d'occupation de chaque caissier
     * @return double
     */
    public double getTauxOccupation() {
        return (Double.parseDouble(tempsOccupation + "") / SimulationData.getTempsTotalOccupation()) * 100;
    }
}
