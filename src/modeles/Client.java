package modeles;

public class Client {
    public static int nbClient = 0;

    private int id;
    private boolean estServi;
    private int heureArrivee;
    private int heureDebutService;
    private int heureSortie;

    public Client(int heureArrivee) {
        Client.nbClient++;
        this.id = nbClient;
        this.heureArrivee = heureArrivee;
        this.estServi = false;
        this.heureDebutService = 0;
        this.heureSortie = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEstServi() {
        return estServi;
    }

    public void setEstServi(boolean estServi) {
        this.estServi = estServi;
    }

    public int getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(int heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public int getHeureSortie() {
        return heureSortie;
    }

    public void setHeureSortie(int heureSortie) {
        this.heureSortie = heureSortie;
    }

    public int getHeureDebutService() {
        return heureDebutService;
    }

    public void setHeureDebutService(int heureDebutService) {
        this.heureDebutService = heureDebutService;
    }
}
