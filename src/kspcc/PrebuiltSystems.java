package kspcc;


public class PrebuiltSystems
{
    
    public static final Body Sun = new Body("Sun", 0, 0, 0, 0, 0, null);
    
    private static final Body Moho   = new Body("Moho",    5.263138304, 0.200f,  7.0,    70.0,  15.0, Sun);
    private static final Body Eve    = new Body("Eve",     9.832684544, 0.010f,  2.1,    15.0,   0.0, Sun);
    private static final Body Gilly  = new Body("Gilly",   0.031500000, 0.550f, 12.0,    10.0,  10.0, Eve);
    private static final Body Kerbin = new Body("Kerbin", 13.599840256, 0.000f,  0.0,     0.0,   0.0, Sun);
    private static final Body Mun    = new Body("Mun",     0.012000000, 0.000f,  0.0,     0.0,   0.0, Kerbin);
    private static final Body Minmus = new Body("Minmus",  0.047000000, 0.000f,  6.0,    78.0,  38.0, Kerbin);
    private static final Body Duna   = new Body("Duna",   20.726155264, 0.051f,  0.06,  135.0,   0.0, Sun);
    private static final Body Ike    = new Body("Ike",     0.003200000, 0.030f,  0.02,    0.0,   0.0, Duna);
    private static final Body Dres   = new Body("Dres",   40.839348203, 0.145f,  5.0,   280.0,  90.0, Sun);
    private static final Body Jool   = new Body("Jool",   68.773560320, 0.050f,  1.304,  52.0,   0.0, Sun);
    private static final Body Laythe = new Body("Laythe",  0.027184000, 0.000f,  0.0,     0.0,   0.0, Jool);
    private static final Body Vall   = new Body("Vall",    0.043152000, 0.000f,  0.0,     0.0,   0.0, Jool);
    private static final Body Tylo   = new Body("Tylo",    0.068500000, 0.000f,  0.025,   0.0,   0.0, Jool);
    private static final Body Bop    = new Body("Bop",     0.128500000, 0.235f, 15.0,    10.0,  25.0, Jool);
    private static final Body Pol    = new Body("Pol",     0.179890000, 0.171f,  4.25,    2.0,  15.0, Jool);
    private static final Body Eeloo  = new Body("Eeloo",  90.118820000, 0.260f,  6.15,   50.0, 260.0, Sun);
    
    public static Body[] vanillaBodies =
    {   Moho,
        Eve,
            Gilly,
        Kerbin,
            Mun,
            Minmus,
        Duna,
            Ike,
        Dres,
        Jool,
            Laythe,
            Vall,
            Tylo,
            Bop,
            Pol,
        Eeloo
    };
    
    private static final Body Sarnus = new Body("Sarnus", 125.798522368, 0.0534f,        2.02, 184.0,   0.0, Sun);
    private static final Body Hale   = new Body("Hale",     0.010488231, 0.0f,           1.00,  55.0,   0.0, Sarnus);
    private static final Body Ovok   = new Body("Ovok",     0.012169413, 0.01f,          1.50,  55.0,   0.0, Sarnus);
    private static final Body Eeloo2 = new Body("Eeloo2",   0.019105978, 0.0034f,        2.30,  55.0, 260.0, Sarnus);
    private static final Body Slate  = new Body("Slate",    0.042592946, 0.04f,          2.30,  55.0,   0.0, Sarnus);
    private static final Body Tekto  = new Body("Tekto",    0.097355304, 0.0034f,        2.30,  55.0,   0.0, Sarnus);
    private static final Body Urlum  = new Body("Urlum",  254.317012787, 0.045214674f,   0.64,  61.0,   0.0, Sun);
    private static final Body Tal    = new Body("Tal",      0.003109163, 0.0f,           1.9,   40.0,   0.0, Urlum);
    private static final Body Polta  = new Body("Polta",    0.011727895, 0.015f,         2.45,  40.0,  60.0, Urlum);
    private static final Body Priax  = new Body("Priax",    0.011727895, 0.015f,         2.45,  40.0,  60.0, Urlum);
    private static final Body Wal    = new Body("Wal",      0.067553668, 0.023f,         1.9,   40.0,   0.0, Urlum);
    private static final Body Neidon = new Body("Neidon", 409.355191706, 0.0127567f,     1.27, 259.0,   0.0, Sun);
    private static final Body Thatmo = new Body("Thatmo",   0.032300895, 0.00043f,     161.1,   66.0,   0.0, Neidon);
    private static final Body Nissee = new Body("Nissee",   0.487743514, 0.72f,         29.56,  66.0,   0.0, Neidon);
    private static final Body Plock  = new Body("Plock",  535.833706086, 0.26f,          6.15, 260.0,  50.0, Sun);
    private static final Body Karen  = new Body("Karen",    0.002457800, 0.0f,           0.0,  260.0,  50.0, Plock);
    
    public static Body[] OPMBodies =
    {   Sarnus,
            Hale,
            Ovok,
            Eeloo2,
            Slate,
            Tekto,
        Urlum,
            Tal,
            Polta,
            Priax,
            Wal,
        Neidon,
            Thatmo,
            Nissee,
        Plock,
            Karen
    };
    
    private static final Body Mercury   = new Body("Mercury",     57.90908185997428,     0.2056264110797241f,     28.552245205587443,   11.00389273075962,     67.47422409882562,     Sun);
    private static final Body Venus     = new Body("Venus",      108.20860704627557,     0.006783584260519895f,   24.43313318920893,     8.015191876420234,    123.8986906325162,     Sun);
    private static final Body Earth     = new Body("Earth",      149.59808647233154,     0.016725458516403675f,   23.439148013195584,    0.003681190405224785, 101.5983652436283,     Sun);
    private static final Body Moon      = new Body("Moon",         0.3833977539407505,   0.05554874558397821f,    23.51739993749185,   358.62963698717,        278.297186632491,      Earth);
    private static final Body Ryugu     = new Body("Ryugu",      178.18894058825955,      0.19114462765182005f,     22.25910858149316,     345.1315346117581,   116.3406974705481,    Sun);
    private static final Body Eros      = new Body("Eros",       218.14075652251505,      0.2228201346169002f,     30.806771887564942,    342.4560837656085,    138.5078510074907,    Sun);
    private static final Body Mars      = new Body("Mars",       227.9390840937713,      0.09337566897226163f,    24.677070649563948,    3.387810351210022,    332.7623776351709,     Sun);
    private static final Body Phobos    = new Body("Phobos",       0.009378544976392461, 0.015108989063044604f,   37.11911628130727,    49.05193498330502,     155.8381702841289,     Mars);
    private static final Body Deimos    = new Body("Deimos",       0.02345895310428892,  0.0002739096426992735f,  36.385954991348406,   47.59869745723073,     281.3537890762374,     Mars);
    private static final Body Vesta     = new Body("Vesta",      353.2753334596891,       0.08937766438777041f,    22.736559484856194,    18.17433683736338,    236.7214644873415,    Sun);
    private static final Body Ceres     = new Body("Ceres",      413.9607412258313,      0.0776567835744355f,     27.148316814094844,    23.58901628075527,    129.7858412721561,     Sun);
    private static final Body Pallas    = new Body("Pallas",     414.53411261347327,     0.23237423954959124f,    11.864726553992485,   162.1825252033153,     322.1737710273642,     Sun);
    private static final Body Ida       = new Body("Ida",        428.0944397558845,       0.04397171501372355f,     24.371118027853846,   358.4493192695172,     75.35388878269467,   Sun);
    private static final Body Dactyl    = new Body("Dactyl",       0.0000834988,          0.13f,                     8.88,                258.76,               220.0,                Ida);
    private static final Body Psyche    = new Body("Psyche",     437.1515235240922,       0.13683200017828878f,     20.802410457280864,     4.198455262629066,   12.39884536441261,   Sun);
    private static final Body Jupiter   = new Body("Jupiter",    778.3437936514404,      0.04832471317106279f,    23.23549871715765,     3.255333559055915,     11.4055930925491,     Sun);
    private static final Body Amalthea  = new Body("Amalthea",     0.1819932970033881,   0.00329777680362021f,    25.35733026078309393, 358.86466510589997370, 108.58726904718403716, Jupiter);
    private static final Body Thebe     = new Body("Thebe",        0.22239865288234115,  0.01755003085100262f,    24.61151104584360638, 359.44315913051320877, 159.39535133195516892, Jupiter);
    private static final Body Io        = new Body("Io",           0.42202992429569966,  0.004176368530521748f,   25.504716492944503,   358.1531277921147,     317.4059012515303,     Jupiter);
    private static final Body Europa    = new Body("Europa",       0.6712611408016664,   0.009362125840338516f,   25.536957752124934,   358.8740650513053,     146.7071594019528,     Jupiter);
    private static final Body Ganymede  = new Body("Ganymede",     1.0706209775439924,   0.0010781676342359267f,  25.487012274584135,   358.0903455173806,      69.5794654380349,     Jupiter);
    private static final Body Callisto  = new Body("Callisto",     1.8831335265952356,   0.007475411373910715f,   25.320516028806185,   358.5581495808171,     319.3185943723458,     Jupiter);
    private static final Body Saturn    = new Body("Saturn",    1428.6150961310217,      0.05379569610078873f,    22.550332783149365,     5.943114763427129,    85.13165122171922,    Sun);
    private static final Body Mimas     = new Body("Mimas",        0.18601845613635162,  0.019680091824605506f,    6.560664042506428,   141.503385279851,      212.6952541470997,     Saturn);
    private static final Body Enceladus = new Body("Enceladus",    0.23841130922430965,  0.004869929354034073f,    6.460911195469927,   130.5162502350598,     245.6141180394775,     Saturn);
    private static final Body Tethys    = new Body("Tethys",       0.29497548406244653,  0.0010295590351270657f,   6.502375211850197,   126.5138016609706,     344.8088544535802,     Saturn);
    private static final Body Dione     = new Body("Dione",        0.3776504080482569,   0.002244359643002204f,    6.459506514051419,   130.5962394996741,     135.4281492231694,     Saturn);
    private static final Body Rhea      = new Body("Rhea",         0.52723301283563113,  0.0010287704879139096f,   6.492182838848735,   132.9839006505602,     156.4986340827077,     Saturn);
    private static final Body Titan     = new Body("Titan",        1.2219471431837277,   0.02879186881388379f,     6.274749159052168,   128.6807038045094,     180.9763313203977,     Saturn);
    private static final Body Hyperion  = new Body("Hyperion",     1.4814605687874002,   0.10236328053757193f,     6.777182377029639,   129.8965814683214,      83.56850835151376,    Saturn);
    private static final Body Iapetus   = new Body("Iapetus",      3.5617232674616966,   0.028359255488871523f,   14.686984140075939,    50.17717250139083,    315.0414033188339,     Saturn);
    private static final Body Phoebe    = new Body("Phoebe",      12.932699012348679,    0.16528481048703042f,   154.18131399931298,    192.8090388522437,     275.348808684514,      Saturn);
    private static final Body Uranus    = new Body("Uranus",    2875.99135062615,        0.04746278576445213f,    23.663165824684135,     1.851312393164233,   168.5359740944419,     Sun);
    private static final Body Puck      = new Body("Puck",         0.08605242564509769,  0.00017412205323346f,    74.57020262902835839, 167.09269302452702277,  52.28461535399433302, Uranus);
    private static final Body Miranda   = new Body("Miranda",      0.1298735083244245,   0.0013650043100019184f,  74.74129991649347,    168.2937266663549,     348.166853506254,      Uranus);
    private static final Body Ariel     = new Body("Ariel",        0.19094590288248107,  0.001979738824610015f,   74.83280196983935,    167.3006634313207,     167.4410432116752,     Uranus);
    private static final Body Umbriel   = new Body("Umbriel",      0.26599881070105353,  0.003941921441915416f,   74.84318753928288,    167.2569091962987,     220.6296154996076,     Uranus);
    private static final Body Titania   = new Body("Titania",      0.4362979609446413,   0.0019963630761945335f,  74.96238167518435,    167.3690834385243,     202.9210194972311,     Uranus);
    private static final Body Oberon    = new Body("Oberon",       0.5835171301910931,   0.0011316902425716753f,  74.91670998579036,    167.4068490041914,     173.9499954619338,     Uranus);
    private static final Body Neptune   = new Body("Neptune",   4506.364703415005,       0.007996842897810068f,   22.296767251929342,     3.478264939490695,    31.21141707518716,    Sun);
    private static final Body Proteus   = new Body("Proteus",      0.11767428267254697,  0.00048555163415778f,    47.38103374585287497,  28.83229670637726016,  78.84576341618641493, Neptune);
    private static final Body Triton    = new Body("Triton",       0.3547661945533973,   2.2364549769683975e-5f, 110.88677266069564,    197.9159979256902,     246.990110747575,      Neptune);
    private static final Body Neired    = new Body("Neired",       5.515078496067815,    0.7508304350381406f,     27.47930633750081,    353.6118859739889,     261.1135451699502,     Neptune);
    private static final Body Pluto     = new Body("Pluto",     5919.905118589132,       0.25005681149465486f,    23.448934406342495,    43.98961521261436,    186.1262383624072,     Sun);
    private static final Body Charon    = new Body("Charon",       0.019595762733426083, 0.00016087158851876902f, 96.2448179088209,     223.0047960769797,     157.0286339712183,     Pluto);
    private static final Body Styx      = new Body("Styx",         0.04316916529937419,  0.024753013324606467f,   96.24519712877296,    223.054438772648,      218.4816352909446,     Pluto);
    private static final Body Nix       = new Body("Nix",          0.04933479682739463,  0.015347675456306756f,   96.24484503469961,    222.9850992906505,      73.18715021883015,    Pluto);
    private static final Body Kerberos  = new Body("Kerberos",     0.05827514495942183,  0.009885082250798403f,   96.24606515812516,    223.4166704164823,     98.29922252822188,     Pluto);
    private static final Body Hydra     = new Body("Hydra",        0.06518175190750335,  0.008565292831581688f,   96.2461719792817,     222.7572796137852,      73.12613265975267,    Pluto);
    private static final Body Arrokoth  = new Body("Arrokoth",  6627.381459600103,       0.038855478225981094f,   21.168584656733213,     2.407701427527428,   346.4287365705793,     Sun);
    
    public static Body[] solBodies =
    {   Mercury,
        Venus,
        Earth,
            Moon,
        Ryugu,
        Mars,
            Phobos,
            Deimos,
        Vesta,
        Ceres,
        Pallas,
        Ida,
            Dactyl,
        Psyche,
        Jupiter,
            Amalthea,
            Thebe,
            Io,
            Europa,
            Ganymede,
            Callisto,
        Saturn,
            Mimas,
            Enceladus,
            Tethys,
            Dione,
            Rhea,
            Titan,
            Hyperion,
            Iapetus,
            Phoebe,
        Uranus,
            Puck,
            Miranda,
            Ariel,
            Umbriel,
            Titania,
            Oberon,
        Neptune,
            Proteus,
            Triton,
            Neired,
        Pluto,
            Charon,
            Styx,
            Nix,
            Kerberos,
            Hydra,
        Arrokoth};
    
    //MPE
    private static final Body Edas      = new Body("Edas",       21.809870000,   0.2226f,   10.828,   304.32,    178.82,    Sun);
    private static final Body Vant      = new Body("Vant",       35.331876000,   0.08874f,   7.14043, 103.85136, 151.19853, Sun);
    private static final Body Zore      = new Body("Zore",       43.692100000,   0.14f,      3.095,   150.352,   228.047,   Sun);
    private static final Body LintMikey = new Body("LintMikey",  51.806000000,   0.64102f,   7.0405,  122.11,     12.78,    Sun);
    private static final Body Crokslev  = new Body("Crokslev",   68.773560320,   0.03f,     11.5,     300.43,    242.9,     Sun);
    private static final Body Mracksis  = new Body("Mracksis",  114.179294679,   0.16126f,   7.5,     122.11,    294.834,   Sun);
    private static final Body Flake     = new Body("Flake",       0.000659162,   0.05f,      5.0,      12.0,     213.0,     Mracksis);
    private static final Body Havous    = new Body("Havous",    108.782986529,   0.19f,     13.21,    122.11,    237.56,    Sun);
    private static final Body Kal       = new Body("Kal",         0.000406750,   0.001f,     0.02,    245.0,      34.0,     Havous);
    private static final Body KiKi      = new Body("Ki'Ki",       0.003526750,   0.11f,     11.45,    256.0,     123.0,     Havous);
    private static final Body Geito     = new Body("Geito",     153.396400000,   0.96714f, 102.26,     58.42,    161.33,    Sun);
    private static final Body Ervo      = new Body("Ervo",      170.252514114,   0.4f,      10.5,      35.9,     151.7,     Sun);
    private static final Body Archae    = new Body("Archae",      0.001486000,   0.03f,      1.2,     130.0,      30.0,     Ervo);
    private static final Body Soden     = new Body("Soden",    1209.633200, 0.84123f,  11.929,   144.32,    311.53,    Sun);
    private static final Body Lon       = new Body("Lon",         0.001462950,   0.2f,       9.8,     124.0,       7.0,     Soden);
    
    public static Body[] MPEBodies =
    {   Edas,
        Vant,
        Zore,
        LintMikey,
        Crokslev,
        Mracksis,
            Flake,
        Havous,
            Kal,
            KiKi,
        Geito,
        Ervo,
            Archae,
        Soden,
            Lon
    };
}
