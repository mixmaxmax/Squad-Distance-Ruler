import java.util.*;

public class GameMapManager {
    private static final Map<String, GameMap> ALL_MAPS = new LinkedHashMap<>();

    static {
        initJensensRange();
        initAlBasrah();
        initAnvil();
//        initBelayaPass();
        initBlackCoast();
//        initChora();
        initFallujah();
        initFoolsRoad();
        initGooseBay();
        initGorodok();
//        initHarju();
//        initKamdeshHighlands();
//        initKohatToi();
//        initKokan();
//        initLashkarValley();
//        initLogarValley();
        initManicougan();
//        initMestia();
        initMutaha();
        initNarva();
//        initPacificProvingGrounds();
        initSanxianIslands();
        initSkorpo();
//        initSumariBala();
//        initTalilOutskirts();
        initYegoryevka();
    }

    private static void initJensensRange() {
        Map<String, Map<String, double[]>> jensensRangeCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/174.0,
                900.0/260.0,
                900.0/390.0,
                300.0/195.0,
                300.0/293.0,
                100.0/147.0,
                100.0/220.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/216.0,
                900.0/324.0,
                900.0/485.0,
                300.0/243.0,
                300.0/364.0,
                300.0/546.0,
                100.0/273.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/181.0,
                900.0/270.0,
                900.0/405.0,
                300.0/203.0,
                300.0/304.0,
                300.0/456.0,
                100.0/228.0,
                100.0/268.0
        });
        jensensRangeCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/232.0,
                900.0/347.0,
                900.0/520.0,
                300.0/261.0,
                300.0/390.0,
                100.0/196.0,
                100.0/293.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/288.0,
                900.0/431.0,
                900.0/647.0,
                300.0/324.0,
                300.0/485.0,
                300.0/727.0,
                100.0/364.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/232.0,
                900.0/347.0,
                900.0/520.0,
                300.0/261.0,
                300.0/390.0,
                100.0/196.0,
                100.0/293.0,
                100.0/344.0
        });
        jensensRangeCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = new HashMap<>();
        uhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/347.0,
                900.0/520.0,
                900.0/780.0,
                300.0/391.0,
                300.0/585.0,
                100.0/293.0,
                100.0/439.0,
                100.0/515.0
        });
        uhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/432.0,
                900.0/648.0,
                900.0/970.0,
                300.0/486.0,
                300.0/728.0,
                300.0/1091.0,
                100.0/547.0,
                100.0/641.0
        });
        uhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/361.0,
                900.0/540.0,
                900.0/810.0,
                300.0/406.0,
                300.0/608.0,
                300.0/911.0,
                100.0/457.0,
                100.0/535.0
        });
        jensensRangeCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("JENSENSRANGE", "Jensen's Range", 8, jensensRangeCoeffs));
    }

    private static void initAlBasrah(){
        Map<String, Map<String, double[]>> alBasrahCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/174.0,
                900.0/261.0,
                900.0/391.0,
                300.0/196.0,
                300.0/293.0,
                100.0/147.0,
                100.0/220.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/217.0,
                900.0/324.0,
                900.0/486.0,
                300.0/244.0,
                300.0/365.0,
                300.0/547.0,
                100.0/274.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/181.0,
                900.0/271.0,
                900.0/406.0,
                300.0/203.0,
                300.0/305.0,
                300.0/457.0,
                100.0/229.0,
                100.0/268.0
        });
        alBasrahCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/232.0,
                900.0/348.0,
                900.0/510.0,
                300.0/261.0,
                300.0/391.0,
                100.0/196.0,
                100.0/294.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/289.0,
                900.0/432.0,
                900.0/648.0,
                300.0/324.0,
                300.0/486.0,
                300.0/729.0,
                100.0/365.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/241.0,
                900.0/361.0,
                900.0/541.0,
                300.0/271.0,
                300.0/406.0,
                300.0/608.0,
                100.0/305.0,
                100.0/357.0
        });
        alBasrahCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        alBasrahCoeffs.put("3840x2160", uhdCoeffs);
        addMap(new GameMap("ALBASRAH", "Al Basrah", 8, alBasrahCoeffs));
    }
    private static void initAnvil(){
        Map<String, Map<String, double[]>> anvilCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/227.0,
                900.0/341.0,
                300.0/171.0,
                300.0/256.0,
                300.0/383.0,
                100.0/192.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/283.0,
                900.0/424.0,
                900.0/635.0,
                300.0/318.0,
                300.0/477.0,
                100.0/239.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/236.0,
                900.0/353.0,
                300.0/177.0,
                300.0/266.0,
                300.0/398.0,
                100.0/200.0,
                100.0/268.0
        });
        anvilCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/303.0,
                900.0/454.0,
                300.0/228.0,
                300.0/341.0,
                300.0/511.0,
                100.0/256.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/377.0,
                900.0/565.0,
                900.0/847.0,
                300.0/424.0,
                300.0/635.0,
                100.0/318.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/315.0,
                900.0/427.0,
                300.0/236.0,
                300.0/354.0,
                300.0/530.0,
                100.0/266.0,
                100.0/357.0
        });
        anvilCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        anvilCoeffs.put("3840x2160", uhdCoeffs);
        addMap(new GameMap("ANVIL", "Anvil", 7, anvilCoeffs));
    }

    private static void initBlackCoast(){
        Map<String, Map<String, double[]>> blackCoastCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/152.0,
                900.0/227.0,
                900.0/340.0,
                300.0/170.0,
                300.0/255.0,
                300.0/382.0,
                100.0/192.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/189.0,
                900.0/282.0,
                900.0/423.0,
                900.0/634.0,
                300.0/317.0,
                300.0/476.0,
                100.0/238.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/158.0,
                900.0/236.0,
                900.0/353.0,
                300.0/177.0,
                300.0/265.0,
                300.0/397.0,
                100.0/199.0,
                100.0/268.0
        });
        blackCoastCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/202.0,
                900.0/303.0,
                900.0/453.0,
                300.0/227.0,
                300.0/340.0,
                300.0/510.0,
                100.0/256.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/251.0,
                900.0/376.0,
                900.0/563.0,
                900.0/844.0,
                300.0/423.0,
                300.0/634.0,
                100.0/317.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/210.0,
                900.0/314.0,
                900.0/471.0,
                300.0/236.0,
                300.0/353.0,
                300.0/529.0,
                100.0/265.0,
                100.0/357.0
        });
        blackCoastCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        blackCoastCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("BLACKCOAST", "Black Coast", 8, blackCoastCoeffs));
    }

    private static void initFallujah(){
        Map<String, Map<String, double[]>> fallujahCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/231.0,
                900.0/347.0,
                300.0/174.0,
                300.0/260.0,
                300.0/390.0,
                100.0/195.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/288.0,
                900.0/431.0,
                900.0/647.0,
                300.0/324.0,
                300.0/485.0,
                100.0/243.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/241.0,
                900.0/360.0,
                300.0/181.0,
                300.0/270.0,
                300.0/405.0,
                100.0/203.0,
                100.0/268.0
        });
        fallujahCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/309.0,
                900.0/463.0,
                300.0/232.0,
                300.0/347.0,
                300.0/520.0,
                100.0/261.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/384.0,
                900.0/575.0,
                900.0/862.0,
                300.0/431.0,
                300.0/646.0,
                100.0/324.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/321.0,
                900.0/480.0,
                300.0/241.0,
                300.0/360.0,
                300.0/540.0,
                100.0/271.0,
                100.0/357.0
        });
        fallujahCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        fallujahCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("FALLUJAH", "Fallujah", 7, fallujahCoeffs));
    }

    private static void initFoolsRoad(){
        Map<String, Map<String, double[]>> foolsRoadCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/392.0,
                300.0/196.0,
                300.0/294.0,
                100.0/147.0,
                100.0/221.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/487.0,
                300.0/244.0,
                300.0/366.0,
                300.0/547.0,
                100.0/275.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/407.0,
                300.0/204.0,
                300.0/305.0,
                300.0/458.0,
                100.0/229.0,
                100.0/268.0
        });
        foolsRoadCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/522.0,
                300.0/262.0,
                300.0/392.0,
                100.0/197.0,
                100.0/294.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/649.0,
                300.0/325.0,
                300.0/487.0,
                300.0/730.0,
                100.0/365.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/542.0,
                300.0/272.0,
                300.0/407.0,
                300.0/610.0,
                100.0/305.0,
                100.0/357.0
        });
        foolsRoadCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        foolsRoadCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("FOOLSROAD", "Fool's Road", 6, foolsRoadCoeffs));
    }

    private static void initGooseBay(){
        Map<String, Map<String, double[]>> gooseBayCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/173.0,
                900.0/259.0,
                900.0/388.0,
                300.0/194.0,
                300.0/291.0,
                100.0/146.0,
                100.0/218.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/215.0,
                900.0/322.0,
                900.0/482.0,
                300.0/242.0,
                300.0/362.0,
                300.0/542.0,
                100.0/272.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/180.0,
                900.0/269.0,
                900.0/403.0,
                300.0/202.0,
                300.0/302.0,
                300.0/453.0,
                100.0/227.0,
                100.0/268.0
        });
        gooseBayCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/231.0,
                900.0/345.0,
                900.0/517.0,
                300.0/259.0,
                300.0/388.0,
                100.0/195.0,
                100.0/292.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/286.0,
                900.0/429.0,
                900.0/643.0,
                300.0/322.0,
                300.0/482.0,
                300.0/723.0,
                100.0/362.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/239.0,
                900.0/358.0,
                900.0/537.0,
                300.0/269.0,
                300.0/403.0,
                300.0/604.0,
                100.0/302.0,
                100.0/357.0
        });
        gooseBayCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        gooseBayCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("GOOSEBAY", "Goose Bay", 8, gooseBayCoeffs));
    }

    private static void initGorodok(){
        Map<String, Map<String, double[]>> gorodokCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/171.0,
                900.0/257.0,
                900.0/385.0,
                300.0/193.0,
                300.0/289.0,
                100.0/145.0,
                100.0/217.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/213.0,
                900.0/319.0,
                900.0/478.0,
                300.0/240.0,
                300.0/359.0,
                300.0/538.0,
                100.0/270.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/178.0,
                900.0/267.0,
                900.0/400.0,
                300.0/200.0,
//                300.0/300.0,
                1,
                300.0/449.0,
                100.0/225.0,
                100.0/268.0
        });
        gorodokCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/229.0,
                900.0/342.0,
                900.0/513.0,
                300.0/257.0,
                300.0/385.0,
                100.0/193.0,
                100.0/289.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/284.0,
                900.0/425.0,
                900.0/638.0,
                300.0/319.0,
                300.0/479.0,
                300.0/717.0,
                100.0/359.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/237.0,
                900.0/355.0,
                900.0/532.0,
                300.0/267.0,
                300.0/400.0,
                300.0/599.0,
                100.0/300.0,
                100.0/357.0
        });
        gorodokCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        gorodokCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("GORODOK", "Gorodok", 8, gorodokCoeffs));
    }

    private static void initManicougan(){
        Map<String, Map<String, double[]>> manicouganCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/173.0,
                900.0/259.0,
                900.0/388.0,
                300.0/194.0,
                300.0/291.0,
                100.0/146.0,
                100.0/218.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/215.0,
                900.0/322.0,
                900.0/482.0,
                300.0/242.0,
                300.0/362.0,
                300.0/542.0,
                100.0/272.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/180.0,
                900.0/269.0,
                900.0/403.0,
                300.0/202.0,
                300.0/302.0,
                300.0/453.0,
                100.0/227.0,
                100.0/268.0
        });
        manicouganCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/231.0,
                900.0/345.0,
                900.0/517.0,
                300.0/259.0,
                300.0/388.0,
                100.0/195.0,
                100.0/292.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/286.0,
                900.0/429.0,
                900.0/643.0,
                300.0/322.0,
                300.0/482.0,
                300.0/723.0,
                100.0/362.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/239.0,
                900.0/358.0,
                900.0/537.0,
                300.0/269.0,
                300.0/403.0,
                300.0/604.0,
                100.0/302.0,
                100.0/357.0
        });
        manicouganCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        manicouganCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("MANICOUGAN", "Manicougan", 8, manicouganCoeffs));
    }

    private static void initMutaha(){
        Map<String, Map<String, double[]>> mutahaCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/252.0,
                900.0/378.0,
                300.0/190.0,
                300.0/284.0,
                300.0/425.0,
                100.0/213.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/314.0,
                900.0/471.0,
                300.0/236.0,
                300.0/353.0,
                300.0/529.0,
                100.0/265.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/262.0,
                900.0/393.0,
                300.0/197.0,
                300.0/295.0,
                300.0/442.0,
                100.0/222.0,
                100.0/268.0
        });
        mutahaCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/337.0,
                900.0/505.0,
                300.0/253.0,
                300.0/379.0,
                300.0/567.0,
                100.0/284.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/418.0,
                900.0/627.0,
                300.0/314.0,
                300.0/471.0,
                300.0/705.0,
                100.0/353.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/349.0,
                900.0/524.0,
                300.0/262.0,
                300.0/393.0,
                300.0/589.0,
                100.0/295.0,
                100.0/357.0
        });
        mutahaCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        mutahaCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("MUTAHA", "Mutaha", 7, mutahaCoeffs));
    }

    private static void initNarva(){
        Map<String, Map<String, double[]>> narvaCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/248.0,
                900.0/372.0,
                300.0/186.0,
                300.0/279.0,
                300.0/419.0,
                100.0/210.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/309.0,
                900.0/463.0,
                300.0/232.0,
                300.0/348.0,
                300.0/521.0,
                100.0/261.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/258.0,
                900.0/387.0,
                300.0/194.0,
                300.0/290.0,
                300.0/434.0,
                100.0/218.0,
                100.0/268.0
        });
        narvaCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/331.0,
                900.0/496.0,
                300.0/249.0,
                300.0/373.0,
                300.0/558.0,
                100.0/280.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/412.0,
                900.0/617.0,
                300.0/309.0,
                300.0/463.0,
                300.0/694.0,
                100.0/348.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/344.0,
                900.0/515.0,
                300.0/258.0,
                300.0/387.0,
                300.0/580.0,
                100.0/290.0,
                100.0/356.0
        });
        narvaCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        narvaCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("NARVA", "Narva", 7, narvaCoeffs));
    }

    private static void initSanxianIslands() {
        Map<String, Map<String, double[]>> sanxianIslandsCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/152.0,
                900.0/227.0,
                900.0/340.0,
                300.0/170.0,
                300.0/255.0,
                300.0/382.0,
                100.0/192.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/189.0,
                900.0/282.0,
                900.0/423.0,
                900.0/634.0,
                300.0/317.0,
                300.0/476.0,
                100.0/238.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/158.0,
                900.0/236.0,
                900.0/353.0,
                300.0/177.0,
                300.0/265.0,
                300.0/397.0,
                100.0/199.0,
                100.0/268.0
        });
        sanxianIslandsCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/202.0,
                900.0/303.0,
                900.0/453.0,
                300.0/227.0,
                300.0/340.0,
                300.0/510.0,
                100.0/256.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/251.0,
                900.0/376.0,
                900.0/563.0,
                900.0/845.0,
                300.0/423.0,
                300.0/634.0,
                100.0/317.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/210.0,
                900.0/314.0,
                900.0/471.0,
                300.0/236.0,
                300.0/353.0,
                300.0/529.0,
                100.0/265.0,
                100.0/357.0
        });
        sanxianIslandsCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        sanxianIslandsCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("SANXIANISLANDS", "Sanxian Islands", 8, sanxianIslandsCoeffs));
    }

    private static void initSkorpo() {
        Map<String, Map<String, double[]>> skorpoCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/102.0,
                900.0/153.0,
                900.0/229.0,
                900.0/342.0,
                300.0/172.0,
                300.0/257.0,
                300.0/385.0,
                100.0/193.0,
                100.0/258.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/127.0,
                900.0/190.0,
                900.0/284.0,
                900.0/426.0,
                900.0/638.0,
                300.0/320.0,
                300.0/479.0,
                100.0/240.0,
                100.0/321.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/106.0,
                900.0/159.0,
                900.0/237.0,
                900.0/356.0,
                300.0/178.0,
                300.0/267.0,
                300.0/400.0,
                100.0/201.0,
                100.0/268.0
        });
        skorpoCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/136.0,
                900.0/204.0,
                900.0/305.0,
                900.0/457.0,
                300.0/229.0,
                300.0/343.0,
                300.0/514.0,
                100.0/258.0,
                100.0/345.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/169.0,
                900.0/253.0,
                900.0/379.0,
                900.0/567.0,
                900.0/851.0,
                300.0/426.0,
                300.0/638.0,
                100.0/320.0,
                100.0/428.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/141.0,
                900.0/211.0,
                900.0/316.0,
                900.0/474.0,
                300.0/237.0,
                300.0/356.0,
                300.0/533.0,
                100.0/267.0,
                100.0/358.0
        });
        skorpoCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        skorpoCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("SKORPO", "Skorpo", 9, skorpoCoeffs));
    }

    private static void initYegoryevka() {
        Map<String, Map<String, double[]>> yegoryevkaCoeffs = new HashMap<>();

        Map<String, double[]> fhdCoeffs = new HashMap<>();
        fhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/110.0,
                900.0/165.0,
                900.0/246.0,
                900.0/369.0,
                300.0/185.0,
                300.0/277.0,
                300.0/415.0,
                100.0/208.0,
                100.0/257.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/137.0,
                900.0/205.0,
                900.0/306.0,
                900.0/459.0,
                300.0/230.0,
                300.0/345.0,
                300.0/517.0,
                100.0/259.0,
                100.0/320.0
        });
        fhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/114.0,
                900.0/171.0,
                900.0/256.0,
                900.0/384.0,
                300.0/192.0,
                300.0/288.0,
                300.0/431.0,
                100.0/216.0,
                100.0/268.0
        });
        yegoryevkaCoeffs.put("1920x1080", fhdCoeffs);

        Map<String, double[]> qhdCoeffs = new HashMap<>();
        qhdCoeffs.put("MAP_ON_DEFAULT_M", new double[]{
                900.0/147.0,
                900.0/220.0,
                900.0/329.0,
                900.0/493.0,
                300.0/247.0,
                300.0/370.0,
                300.0/554.0,
                100.0/278.0,
                100.0/344.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_CAPSLOCK", new double[]{
                900.0/182.0,
                900.0/273.0,
                900.0/409.0,
                900.0/612.0,
                300.0/307.0,
                300.0/459.0,
                300.0/689.0,
                100.0/344.0,
                100.0/427.0
        });
        qhdCoeffs.put("MAP_ON_DEFAULT_ENTER", new double[]{
                900.0/152.0,
                900.0/228.0,
                900.0/341.0,
                900.0/511.0,
                300.0/256.0,
                300.0/384.0,
                300.0/575.0,
                100.0/288.0,
                100.0/357.0
        });
        yegoryevkaCoeffs.put("2560x1440", qhdCoeffs);

        Map<String, double[]> uhdCoeffs = calculateCoeffsUHD(fhdCoeffs);
        yegoryevkaCoeffs.put("3840x2160", uhdCoeffs);

        addMap(new GameMap("YEGORYEVKA", "Yegoryevka", 9, yegoryevkaCoeffs));
    }

    private static void addMap(GameMap map) {
        ALL_MAPS.put(map.getName(), map);
    }

    public static GameMap getMap(String name) {
        return ALL_MAPS.getOrDefault(name, ALL_MAPS.values().iterator().next());
    }

    public static List<GameMap> getAllMaps() {
        return new ArrayList<>(ALL_MAPS.values());
    }

    public static String[] getAllDisplayNames() {
        return ALL_MAPS.values().stream()
                .map(GameMap::getDisplayName)
                .toArray(String[]::new);
    }

    private static Map<String, double[]> calculateCoeffsUHD(Map<String, double[]> fhdSource) {
        Map<String, double[]> result = new HashMap<>();
        for(Map.Entry<String, double []> entry : fhdSource.entrySet()) {
            double [] src = entry.getValue();
            double [] dst = new double [src.length];
            for (int i = 0; i < src.length; i++) {
                dst[i] = src[i] * 0.5;
            }
            result.put(entry.getKey(), dst);
        }
        return result;
    }
}