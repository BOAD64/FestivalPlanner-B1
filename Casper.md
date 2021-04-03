# Portfolio Casper Lous
Studentnummer 2173246

## Inhoud
1. [Week 1](#week-1)
2. [Week 2](#week-2)
3. [Week 3](#week-3)
4. [Week 4](#week-4)
5. [Week 5](#week-5)
6. [Week 6](#week-6)
7. [Week 7](#week-7)
8. [Reflectie op stelling](#reflectie-op-stelling)
9. [Applicaties die JSON gebruiken](#applicaties-die-json-gebruiken)




## Week 1
In de eerste week van het project moest er gelijk veel gedaan worden, dus er is met de groep zo snel mogelijk een 
vergadering ingepland. Tijdens deze vergadering is kennis gemaakt, zijn de rollen verdeeld en is besloten dat we het 
schoolplanner-project zouden gaan doen. Dit is allemaal goed verlopen en de groep lijkt het snel over zaken eens te 
kunnen worden. Er is vervolgens een samenwerkingscontract opgesteld en er is een begin gemaakt aan het plan van aanpak.

Elk groepslid kreeg een aantal hoofdstukken van het PVA toegewezen om alleen of samen met een ander groepslid aan te werken.
Voor mij waren dat de hoofdstukken planning en kosten- en batenoverzicht. Met name de planning is door een aantal versies geweest,
omdat hij nog niet volgens de eisen van de beoordelend docent was.

Verder waren deze week de eerste tutor- en seniormeetings, waar vergaderd werd, vragen gesteld werden en een eerste ontwerp
voor de GUI van de agendamodule van ons project werd gemaakt. Iedereen heeft daarbij input gegeven en de samenwerking
verliep goed.

## Week 2
Week twee bestond voor het grootste deel uit het maken van documentatie, maar er is ook een begin aan de code gemaakt.
Om het plan van aanpak af te maken ging de taakverdeling deze week iets minder makkelijk, mede doordat er niet helemaal
goed gepland was deze dag. Het werk is uiteindelijk wel succesvol afgemaakt.

Ook is begonnen aan het technisch document. Hiervan is het eerste deel gelijk helemaal afgemaakt, en er is deze dag beter
gewertk dan de dag er voor. De samenwerking verliep beter en er is veel voortgang gemaakt.
Verder heeft er nog een vergadering plaatsgevonden over de voortgang van deze week en over de planning van de komende
twee weken.

De volgende dag is begonnen aan het programmeren. Hiervoor was eerst een vergadering, om het ontwerp dat toegepast zou
worden te overleggen. Dit ontwerp leverde een aantal problemen op. Er is gebruik gemaakt van het model-view-controller
systeem. Dit wil zeggen dat elk onderdeel bestaat uit een klasse met data, een klasse die de data opvraagt en aanpast
en een klasse die zorgt voor visualisatie en gebruikersinteractie. Het is echter niet goed genoeg afgesproken welke
onderdelen van de code in de controllerklasse thuis horen en welke in de viewklasse. Er is voorgesteld om interfaces te 
maken, maar in plaats daarvan heeft 1 iemand alleen verbaal uitgelegd wat er gedaan moest worden. Dit bleek later niet
voldoende om iedereen op dezelfde manier te laten programmeren. Er is later gerefactord om deze problemen op te lossen.

Tijdens het programmeren is samen met Tom gewerkt aan de klasses voor Classroom. Deze samenwerking is goed verlopen, en 
we waren een goede aanvulling op elkaar.

## Week 3
De derde week begon met een vergadering over de voortgang van het project, om iedereen op de hoogte te stellen van de 
activiteiten van anderen. In deze vergadering is afgesproken dat Tom en ik ook verantwoordelijk zouden worden voor
het maken van de zogenaamde MainView. Deze klasse zorgt er voor dat alle losse onderdelen samen op het scherm getoond 
kunnen worden. Ook moest hier een hamburgermenu met een aantal features toegevoegd worden, en een uitklap-menu om 
onderdelen toe te voegen of te verwijderen. Het tonen van het rooster bleek uiteindelijk de grootste uitdaging, omdat
de het lastig bleek om de node op te vragen, in plaats van de hele stage. Hier had tijdens het ontwerp beter over nagedacht
moeten worden.

Uitendelijk is met behulp van advies van de senior, en een hoop trial and error, de MainView werkend gekregen. Met behulp
van een nieuwe methode getNode() is het gelukt om een borderPane terug te krijgen, die in de alles bevattende stackPane
geplaatst kon worden. Deze methode werkt als volgt:

```
public Node getNode() {
        this.view.setAppointments(this.sorter.sort(this.schedule));
        if(this.view.getCanvas() == null) {
            this.view.createStage();
        }
        this.view.getSorterComboBox().setItems(FXCollections.observableList(this.appointmentSorters()));
        this.view.getSorterComboBox().getSelectionModel().select(this.sorter);
        this.view.getSorterComboBox().setOnAction(this::onSorterSelect);
        this.view.getCanvas().setOnMouseClicked(this.onCanvasClick());
        this.view.getCanvas().setWidth(Double.MAX_VALUE);
        this.view.getCanvas().setHeight(Double.MAX_VALUE);
        this.view.draw();
        return this.view.getBorderPane();
    }
```
## Week 4
Net als de week er voor begon week 4 met een vergadering over de voortgang en de planning. Zo is iedereen weer op de
hoogte van het werk van de anderen. Er moest deze week gewerkt worden aan het inlezen van een Tiled-map voor de simulatie
software. Deze map is ontworpen door andere groepsleden, en samen met Lieselotte was ik verantwoordelijk voor het inladen
van de map in het project. De samenwerking verliep hier goed en met behulp van pair programming ging het werk snel en
efficient.

Het inladen van de map heeft een aantal problemen opgeleverd. Het algoritme dat in eerste instantie bedacht is werkte
op zich wel, maar de fout zat met name in het tonen van de map, niet het inladen. De problemen werden veroorzaakt door
het feit dat er tiles op index 0 van de arrays werden opgeslagen, maar Tiled heeft als functionaliteit dat lege tiles
index 0 krijgen. Tegels met index 0 werden dus niet goed afgedrukt, en er moest geschoven worden in de indeces. Toen
dit uiteindelijk gelukt was, en lege tiles als tile (en niet als index) -1 meekregen, werden lege tiles correct afgevangen
en werd de map goed afgedrukt.

```
for (int i = 0; i < layerData.size(); i++) {
    int tileIndex = layerData.getInt(i) - 1;
    if (tileIndex >= 0) {
        tiles.add(new Tile(i % width, i / height, tileIndex));
    }
}
```

## Week 5
Aan het begin van week 5 is de laatste hand gelegd aan het inladen van de Tiled-map, en dit werkt nu volledig. Vervolgens
is een lijst met vragen opgesteld om aan de senior te stellen, het zogenaamde Edwin-lijstje. Echter bleek Johan deze week
Edwin te vervangen als senior, dus heeft hij ons met ons Edwin-lijstje geholpen. Op basis van zijn advies is toen nog
caching toegevoegd aan het laden van de Tiled-map. Ook hadden we nu voldoende informatie om te beginnen aan de NPC's.

## Week 6
Om de tijd binnen de simulatie te kunnen versnellen is afgesproken dat ik samen met Jeroen een klok-klasse zou maken.
Deze klasse houdt de tijd binnen de simulatie bij, en ook hoe snel deze verloopt. Jeroen is deze week echter ziek geworden,
dus ik heb wederom samengewerkt met Tom. Ik werk met Tom altijd prettig samen, vooral in de vorm van pair-programming,
omdat we naar mijn idee elkaar heel goed aanvullen in het programmeren. 

De Clock vond ik persoonlijk een leuke klasse om te schrijven, omdat ik een goed idee had over hoe het gedaan kon worden.
De klasse heeft een speedMultiplier-attribuut. Hiermee wordt bij elke aanroep van update() de tijd binnen de simulatie
geupdatet, door middel van deltaTime. Om er echter voor te zorgen dat ook de andere updatables, zoals de NPC's, hun taken
zouden versnellen, moesten zij de speedMultiplier ook toegepast krijgen. Hiervoor heeft de Clock een functie
getNewDeltaTime(). Deze functie neemt een waarde in voor de originele deltaTime, en geeft een aangepaste deltaTime terug,
op basis van de speedMultiplier. Deze nieuwe deltaTime wordt vervolgens door alle andere updatables gebruikt. Zo worden
bijvoorbeeld de loop-animaties en loopsnelheid van de NPC's ook sneller als de tijd versneld wordt.

```
//method from Clock class
public double getNewDeltaTime(double deltaTime){
    return deltaTime * this.speedMultiplier;
}

//method from Simulation class
public void update(double deltaTime) {
    this.clock.update(deltaTime);
    double newDeltaTime = this.clock.getNewDeltaTime(deltaTime); //use this instead of deltaTime
```

## Week 7
In deze week is extra functionaliteit toegevoegd aan de Clock klasse. Ik heb deze week niet samengewerkt met iemand, 
maar de feature alleen gemaakt. Omdat het een kleine feature was, was dit geen probleem. De Clock moest de funtionaliteit
krijgen om de simulatie te pauzeren, en hiervoor moest een knop in de MainView komen.

Allereest is de methode pause() in de Clock toegevoegd, samen met een attribuut paused, van het type boolean.
Deze methode wordt aangeroepen wanneer de gebruiker op de pauze knop klikt. Dat betekent dat als de simulatie al gepauzeerd
is, en er wordt nog een keer op de knop geklikt, de simulatie weer moet hervatten. Er moet dus eerst gecontroleerd worden
of de simulatie al gepauzeerd is. Op het moment dat de simulatie nog niet gepauzeerd is, wordt de paused boolean op true
gezet, en wordt de huidige speedMultiplier opgeslagen. Vervolgens wordt de speedMultiplier op 0 gezet, waardoor alles stil
komt te staan.

```
public void pause(){
    if (!this.paused){
        this.paused = true;
        this.fontColor = Color.red; //turns clock red when paused
        this.previousSpeedMultiplier = this.speedMultiplier;
        this.speedMultiplier = 0;

    }else {
        this.paused = false;
        this.fontColor = Color.white;
        this.speedMultiplier = this.previousSpeedMultiplier;
    }
}
```

## Reflectie op stelling
**“In het bedrijfsleven wordt steeds meer in software gesimuleerd”**

Om goed te kunnen reflecteren over deze stelling is eerst onderzoek gedaan. Er is gezocht op welke manieren bedrijven
simulatie toe kunnen passen in hun bezigheden, en in welke sectoren dit nuttig is. 

Simulatie zou onder andere in de gezondheidszorg toegepast kunnen worden. Er kan gesimuleerd worden hoe bepaalde processen
verlopen, zoals het verplaatsen en verzorgen van patienten in een ziekenhuis. Zo kan er vervolgens gekeken worden waar
ruimte is voor verbeteringen en meer efficientie. 

Ook in fabrieken kan het nuttig zijn om simulatie te gebruiken. Met name voor het ontwerp van fabrieken kan het nuttig 
zijn om te kijken waar welke machines het best kunnen komen te staan, om zo efficient mogelijk met zowel ruimte als tijd
om te gaan. Door van te voren verschillende opstellingen in een simulatie te testen kan getest worden en een bouwplan 
opgesteld worden.

In andere logistieke bedrijven kan simulatie ook een nuttig gereedschap zijn. In een magazijn of opslag kunnen routes
voor heftrucs of andere manieren van transport uitgetest worden. Ook kunnen verschillende inrichtingen van opslagrekken
getest worden. Verschillende sorteringen kunnen getest worden en er kan gekeken worden welke inrichting het meest
efficient is. [(GoodFirms, 2021)](#goodfirms-2021)

Het kan dus zeker nuttig zijn voor bedrijven om simulatie toe te passen.

Hoewel simulatie in veel sectoren in opkomst is, zijn er teleurstellend weinig echte voorbeelden te vinden van het toepassen
van een simulatie zoals in de hierboven beschreven gevallen. Het simuleren van processen in een fabriek is het enige waarvan
een aantal goede voorbeelden van te vinden zijn. Software als FlexSim is ontwikkeld om fabrieksprocessen te modelleren
en zo bottlenecks op te sporen, maar bevat ook functionaliteiten die in de andere genoemde sectoren toegepast kunnen worden. 
[(FlexSim, 2021)](#flexsim-2021)

In conclusie wordt er inderdaad steeds meer gesimuleerd in het bedrijfsleven, maar nog lang niet zo veel als mogelijk.
Er zijn in heel veel sectoren verscheidene manieren om simulatie toe te passen en daarmee efficientie van processen te
verbeteren.

## Applicaties die JSON gebruiken
JSON, JavaScript Object Notation, is een manier om data op te slaan. Het wordt door veel programmas gebruikt. Er zullen
nu 3 programmas benoemd worden die gebruik maken van JSON om data op te slaan.

De game Minecraft, misschien wel de bekendste game die met behulp van Java gemaakt is, maakt voor verschillende dingen 
gebruik van JSON. Statistics zoals het aantal interacties met bepaalde objecten in de game worden per wereld opgeslagen
in een JSON bestand. Ook achievements, tekst in boeken en loot tables maken hiervan gebruik. Loot tables worden gebruikt
om de kans dat een enemy bepaalde items dropt te bepalen. Een simpele loot talbe van een Shulker, die 100% kans heeft om
een shulker shell te droppen op het moment dat hij door de speler verslagen wordt, ziet er als volgt uit:
```
{
  "pools": [
    {
            "rolls": 1,
            "entries": [
                {
                    "type": "item",
                    "name": "minecraft:shulker_shell",
                    "weight": 1
                }
            ]
        }
    ]
}
```
[(Minecraft Wiki, 2021)](#minecraft-wiki-2021)

Een ander programma dat ook gebruik maakt van JSON is Tiled. Dit is ook gebruikt in ons eigen project. Tiled kan een 
map van tiles exporteren naar een JSON bestand, dat door een ander programma uitgelezen kan worden. Per tegel in de map
wordt dan opgeslagen welke tegel uit de tileset daar moet komen. Dit wordt op zijn beurt weer per layer opgeslagen. Met
behulp van deze JSONbestanden kan in ons project een map ingeladen worden. Deze bestanden zien er als volgt uit:
```
{
"height":100,
"infinite":false,
"layers":[
        {
         "data":[136, 137, 24, 25, 25, ... , 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136, 136],
         "height":100,
         "id":1,
         "name":"floor",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":100,
         "x":0,
         "y":0
        }
    ]
}
        
```
Toen ik mijn harde schijf doorzocht op JSON bestanden, heb ik nog een derde applicatie gevonden die het format gebruikt.
Fall Guys : Ultimate Knockout gebrukt JSON om op te slaat hoe het hoofdmenu van de game in elkaar zit. De positie van 
het logo, en een aantal parameters worden hier opgeslagen en ingesteld:
```
{
	"title" : "Fall Guys: Ultimate Knockout",
	"executable" : "FallGuys_client_game.exe",
	"logo_position"	: "bottom-left",
	"parameters": "",
	"use_cmdline_parameters" : "1",
	"working_directory": "",
	"wait_for_game_process_exit": "0",
	"hide_splash_screen": "0",
	"hide_ui_controls": "0"
}
```

## Bronnen

### GoodFirms, 2021
https://www.goodfirms.co/blog/the-top-8-free-and-open-source-simulation-software

### FlexSim, 2021
https://www.flexsim.com/manufacturing-simulation/

### Minecraft Wiki, 2021
https://minecraft.fandom.com/wiki/JSON








