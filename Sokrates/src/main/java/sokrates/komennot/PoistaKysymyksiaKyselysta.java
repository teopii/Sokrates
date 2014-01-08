package sokrates.komennot;

import sokrates.kayttoliittyma.Tulostamo;
import sokrates.sovelluslogiikka.Kysely;
import sokrates.sovelluslogiikka.KyselyHallinta;
import sokrates.sovelluslogiikka.Kysymys;
import sokrates.util.Lukija;

/**
 *
 * @author Teo
 */
public class PoistaKysymyksiaKyselysta extends Komento {

    public PoistaKysymyksiaKyselysta(Lukija lukija, KyselyHallinta hallinta, String nimi, String seliteSuomeksi, String seliteEnglanniksi) {
        super(lukija, hallinta, nimi, seliteSuomeksi, seliteEnglanniksi);
    }

    @Override
    public boolean suorita() {
        System.out.println(Tulostamo.valitseKohdeKysely());
        Kysely kohdeKysely = super.kayttajanOsoittamaKysely();
        
        if (kohdeKysely == null) {
            System.out.println(Tulostamo.kysymyksiaEiOle());
        } else {
            poistaKysymyksia(kohdeKysely);
        }
        
        return true;
    }

    public void poistaKysymyksia(Kysely kohdeKysely) {
        while (true) {
            System.out.println(Tulostamo.valitsePoistettavaKysymys());
            
            tulostaKysymysVaihtoehdot(kohdeKysely);

            String syoteTeksti = lukija.lueMerkkijono(Tulostamo.kysymys());

            if (syoteTeksti.equals("x")) {
                System.out.println();
                break;
            }

            int syoteLuku = lukija.tulkitseKokonaisluvuksi(syoteTeksti);
            
            Kysymys poistettavaKysymys = kohdeKysely.getKysymykset().get(syoteLuku);
            String kohdeKyselynNimi = kohdeKysely.getNimi();

            if (!(poistettavaKysymys == null)) {
                kohdeKysely.poistaKysymys(poistettavaKysymys);
                System.out.println(Tulostamo.poistettuKysymys(kohdeKyselynNimi));
            }
        }
    }
    
    public void tulostaKysymysVaihtoehdot(Kysely kohdeKysely) {
        for (int i = 0; i < kohdeKysely.getKysymykset().size(); i++) {
            System.out.println("  " + i + " = " + kohdeKysely.getKysymykset().get(i).getKysymysNykyisellaKielella());
        }
        System.out.println("(x = " + Tulostamo.peruuta() + ")\n");
    }
}
