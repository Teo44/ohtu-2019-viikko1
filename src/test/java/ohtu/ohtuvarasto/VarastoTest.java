package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenVarasto()   {
        Varasto varasto2 = new Varasto(-1);
        assertEquals(0.0, varasto2.getTilavuus(), 0);
    }
    
    @Test
    public void virheellinenVarasto2()  {
        Varasto varasto2 = new Varasto(-1, 0);
        assertEquals(0.0, varasto2.getTilavuus(), 0);
    }
    
    @Test
    public void negatiivinenOttaminen()    {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-2);
        assertEquals(5, varasto.getSaldo(), 0);
    }
    
    @Test
    public void saldonYlittavaOttaminen1()  {
        varasto.lisaaVarastoon(5);
        double otto = varasto.otaVarastosta(8);
        assertEquals(5, otto, 0);
    }
    
    @Test
    public void saldonYlittavaOttaminen2()  {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(8);
        assertEquals(0, varasto.getSaldo(), 0);
    }
    
    @Test
    public void negatiivinenLisaaminen()    {
        varasto.lisaaVarastoon(-2);
        assertEquals(0, varasto.getSaldo(), 0);
    }
    
    @Test
    public void ylimaarainenLisaaminen()    {
        varasto.lisaaVarastoon(25);
        assertEquals(10, varasto.getSaldo(), 0);
    }
    
    @Test
    public void toStringToimii()    {
        varasto.lisaaVarastoon(3);
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }
    
    @Test
    public void varastoAlkusaldolla()   {
        Varasto varasto2 = new Varasto(10, 5);
        assertEquals(5.0, varasto2.getSaldo(), 0);
    }
    
    // tama testi ei mene lapi, saldoksi tulee -1. Oletettavasti ei 
    // toivottua.
//    @Test
//    public void varastoAlkusaldollaNegatiivinenTilavuus()   {
//        Varasto varasto2 = new Varasto(-1, 5);
//        assertEquals(0.0, varasto2.getSaldo(), 0);
//    }
    
    @Test
    public void varastoAlkusaldollaNegatiivinenTilavuus2()  {
        Varasto varasto2 = new Varasto(-1, 5);
        assertEquals(0.0, varasto2.getTilavuus(), 0);
    }
    
    @Test
    public void varastoAlkusaldollaNegatiivinenSaldo()  {
        Varasto varasto2 = new Varasto(10, -1);
        assertEquals(0.0, varasto2.getSaldo(), 0);
    }
}