# Tom Martens
Studentnummer: 2168204
<br/>Groep: B1

## Inhoud
1. Week 3
2. Week 4
3. Week 5
4. Week 6
5. Week 7 en Week 8

## Week 3
Deze week heb ik samen met Casper gewerkt aan de main view en onze oude code van week 2 verbeterd. 
Om de main view uit te leggen wat wij hebben gedaan gaan we eerst naar de code van week 2 kijken. 
Wij hadden de taak gekregen om in het klassendiagram hieronder de Group, Classroom, School en Appointment klassen uit te werken. 
Al hoefde we alleen maar Group, School en Classroom uit te werken. 
Aangezien Luuk en Lieselotte onze Appointment hadden overgenomen aangezien ze al bezig waren met de rooster klassen daar. 

![Klassen diagram V1](KlasseDiagramV1.png)

 Casper en ik hadden in het begin nog wat problemen hoe we het moesten programeren maar uiteindelijk hadden we een idee wat we moesten doen. 
 Het probleem wat we hadden was waar we de stage gingen laten zien. 
 Eerst hadden we het idee om de view klassen Aplication te laten extenden en zo iets zichtbaars krijgen. Maar dat kon natuurlijk niet. 
 Daarna lieten we de View klassen een stage laten maken. 
 En in die klassen stage.show Laten doen. Maar na wat overleg met de hele groep waren we er achter gekomen wat de echte bedoeling was. 
 We moesten View een stage laten bouwen. En daarna in de controller stage.show doen. 
 Het uiteindelijke rezultaat zag er dan zo uit.

### Controller klasse voorbeeld
```java
public class GroupController implements Controller {
    private Group group;
    private GroupView groupView;

    public GroupController(Group group) {
        this.group = group;
        this.groupView = new GroupView(group);
    }

    public GroupView getGroupView() {
        return groupView;
    }

    @Override
    public void show() {
        groupView.getStage().show();
    }
}
```
### View klasse voorbeeld
```java
public class GroupView implements View {
    private Group group;

    public GroupView(Group group) {
        this.group = group;
    }

    @Override
    public Stage getStage() {
        //Create complete stage
        return stage;
    }

}
```
Deze structuur hebben wij voor al onze klassen toegepast. Later zou het verbeterd worden maar dit is de basis. 
Deze week 3 hadden wij ook de taak gekregen om dit voor de MainView te doen. En dit leverde veel problemen op. 
De grootste problemen waren dat de main view alle klassen samen voegde en iedereen een andere code structuur en methodes had gebruikt. 
Waar het verwijderen en toevoegen van Sommige classes werkte, werkte het bij andere niet. 
Dit was een gigantisch leermoment en in de toekomst voordat we gingen programmeren maken we afspraken hoe iets behoord te werken en is gemaakt.

## Week 4
Deze week en week 5 heb ik weinig aan code gedaan. 
Jeroen en Luuk waren vooral bezig met het echte verbeteren van de Code die zo slecht samen paste de week hiervoor. 
Ik zelf heb samen met Jochem deze week de eind tiled map getekend in tiled. En deze heeft Casper en Lieselotte ingelezen.
Jochem en ik hadden eest in photoshop zelf een tile set gemaakt. En daarna in tiled een map mee geshilderd met zo logishe mogelijke lagen.
Hoe de map er in het eindproduct uit ziet is hoe wij het hebben getekend. 

## Week 5
Deze week heb ik ook niet echt veel meegeholpen met het programeren. Ik en Jochem hebben Casper geholpen met het inladen van de Tiled map. 
Daar hadden zij nog wat problemen mee. We hadden heel wat problemen gevonden. Grotendeels lagen aan de indexen van de tileset. 
We kwamen erachter dat elke keer dat ons programma een tile probeerde te pakken dat Hij er net een tile naast zat. 
En dus iets verkeerds tekende.

###index probleem
![Index probleem](tomPortfolioResources/buggy index ofset.jpg)
(zo als je kan zien wordt er ook maar 1 layer getekend, de bovenste window layer)  
Na dat we dit probleem hadden opgelost kregen we zo iets:

###Layer probleem
![Layer probleem](tomPortfolioResources/buggy one layerd.jpg)
Zo als je kan zien zijn er zwarte vlekken onder de stoelen bijvoorbeeld. Dit komt doordat er maar een layer wordt getekend. 
En die ene layer tekend dus alleen maar boven de andere layers die al uitgelezen waren. Dit probleem kregen wij maar niet opgelost. 
En da dat wij Luuk er bij hadden gehaald heeft hij er voor gezocht dat lagen boven elkaar worden getekend. en niet inplaats van elkaar.

## Week 6
Deze week bevatte de NPC-klasse. Iets daar Lieselotte en ik ten eerste samen aan hebben gewerkt maar ik later in principe tot mijzelf heb geclaimd.  
In principe werkt NPC hetzelfde als in de les is uitgelegd. We hebben een update die de positie bijhoud en een draw die het poppetje tekent.
```java
public class StudentNPC extends NPC {

    public StudentNPC(Point2D position, double angle) {
        //set all variables
    }

    @Override
    public void setTarget(Point2D position) {
        super.setTarget(position);
    }

    @Override
    public void setSpeed(double speed) {
        super.setSpeed(speed);
    }

    @Override
    public void update() {
        //the basic calculation
        if(target.distanceSq(position) < 10) return;

        this.angle = Math.atan2(this.target.getY() - this.position.getY(), this.target.getX() - this.position.getX());

        this.position = new Point2D.Double(
                this.position.getX() + this.speed * Math.cos(this.angle),
                this.position.getY() + this.speed * Math.sin(this.angle));
    }

    @Override
    public void draw(Graphics2D graphics) {
        //draw chacarter at position
    }
}
```
Na dat we dit hadden geschreven hadden we collision toegevoegd. Maar we wouden niet de collision die in de les was uitgelegd.
Dat was een collision die na een botsing beide characters stilzetten en ze rond liet draaien. Als een soort van slechte boid. 
Wij vonden het veel realistischer als leerlingen elkaar aan de kant duwde. En dat hebben we geïmplementeerd.

```java
    private boolean checkCollision(ArrayList<NPC> collisionNPCs) {
        boolean hasCollision = false;
        for (NPC npc : collisionNPCs) {
            if (npc != this) {
                double thereSize = npc.getHitBoxSize();
                if (npc.getPosition().distanceSq(this.position) < this.hitBoxSize * thereSize + 10) {
                    hasCollision = true;
                    double angleToNPC = Math.atan2(
                            npc.getPosition().getY() -this.position.getY(),
                            npc.getPosition().getX() -this.position.getX());
                    this.position = new Point2D.Double(
                            this.position.getX() - (this.speed / 2) * Math.cos(angleToNPC),
                            this.position.getY() - (this.speed / 2) * Math.sin(angleToNPC));
                }
            }
        }
        return hasCollision;
    }
```
Deze methode implementeert alle collision. Het returnt eerst een boolean of er een collision is geweest. 
Zo ja dan doet de update geen beweging meer. 
De code berekend of er een collision is geweest door te kijken of de distancesq gelijk is aan de hitboxafstanden keer elkaar (plus een beetje voor beter resultaten). 
En daarna komt het gedeelte dat wij samen hebben uitgedacht. 
De “pushback” dit pakt de hoek tussen de 2 NPCs en pusht de current NPC terug zodat er geen intersectie meer is.  
_(grappig fijtje wij hebben bijna 2 uur naar de code zitten staren omdat we deze regel waren vergeten:  __if (npc != this) {__ )_

Na dat we dit hadden gedaan hadden we veel kleine functies toegevoegd zoals.
1. Een person klasse per NPC
2. Een methode die kijkt als de muis wordt gedrukt of de mousPos binen de NPCs hitbox zit. Zo ja open de personKlasse
3. Een update snelheid die wordt geupdate met de een deltatime via de klok classe
4. Een Sprite die voor Student random wordt gekozen via deze code
```java
private void getSprites() {
        try {
            SpriteFile.setPath("/students.png");
            int xOffSet = 0;
            int yOffSet = 0;
            int characterSet = 0;
            while (characterSet == 0) {
                characterSet = (int) Math.round(Math.random() * 3);
            }
            switch (characterSet) {
                case 1:
                    xOffSet = 3;
                    yOffSet = 4 * 12;
                    break;
                case 2:
                    xOffSet = 6;
                    yOffSet = 4 * 12;
                    break;
                case 3:
                    xOffSet = 9;
                    yOffSet = 4 * 12;
                    break;
            }
            int rowStart = 0;

            for (int y = 0; y < 4; y++) {
                rowStart = y * 12;
                for (int x = 0; x < 3; x++) {
                    this.sprites.add(SpriteFile.getSprites().get(rowStart + yOffSet + xOffSet + x));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```
Daarna hebben we alle code van studentNPC naar NPC gezet waar mogelijk om ervoor te zorgen dat teacherNPC de code ook krijgt.
Hierna is de code nog wat aangepast door Luuk om ervoor te zorgen dat NPC de pathfinding kan volgen. Wij gaven namelijk Point2D mee, 
Maar pathfinding gebruikte een andere variable. 
## Week 7 en Week 8
Deze weken heb ik in principe het zelfde gedaan. Waar mogelijk andere helpen en meekijken naar code. Maar vooral aan NPC gewerkt.
Er waren namelijk best wel wat problemen. Vooral met de collision. En beweging op hooge snelheid. en wat kleine bugs die ik kon vinden.
De eerste groote bug die moest worden opgelost is dat NPCs niet meer vooruit konnen lopen zodra de snelheids multiplier boven 10x was.
Dit kwam door deze code:
```java
if (rotation < -(this.rotationSpeed * 100 )) {
            this.angle -= this.rotationSpeed * 100 * deltaTime;
        } else if (rotation > this.rotationSpeed * 100) {
            this.angle += this.rotationSpeed * 100 * deltaTime;
        } else {
            this.angle = targetAngle;
        }
```
waarin het probleem was dat de rotation niet door deltaTime wert vermeendigvuldigt
```java
if (rotation < -(this.rotationSpeed * 100 * deltaTime)) {
            this.angle -= this.rotationSpeed * 100 * deltaTime;
        } else if (rotation > this.rotationSpeed * 100 * deltaTime) {
            this.angle += this.rotationSpeed * 100 * deltaTime;
        } else {
            this.angle = targetAngle;
        }
```
De NPC draaide eerst langzaam rondt met een snelheid die groter werdt hoe hoger tijd snelheid was. 
Dit zorgde ervoor dat de NPC bij een snelheid van 10x+ 180 graden per update omdraaide. En daarna vooruit sprong. 
Dus in principe op zijn plaats aan het trillen was. Door het limiet dat angle = targetAngle verzorgd ook te verhogen wordt op hoge snelheden al snel:
angle = targetAngle gedaan. En de NPC werkt dan zelfs op 60x snelheid. Maar we hebben het alsnog een 20x max vanwege collision (was niet opgelost).

Al heeft collision nog heel veel upgrades gehad. We hadden een probleem dat collision mooi de lopende NPC naar achter zette maar niet de andere NPC naar achtere duwde.
Dit is opgelost Met de code hieronder. Ik had een pushBack methode toegevoegd. deze methode duwt de andere NPC naar achteren.
Maar dit leverde een probleem op. Wat gebeurt er als een die NPC in nog een andere NPC wordt geduwd. Dan moet weer de checkCollision methode aan worden geroepen.
en dan kan je een infinite loop maken als je meer dan 5 NPCs hebt die elkaar aanraken. Dit heb ik opgelost door een iteratorAmount toe te voegen.
Deze staat normaal gesproken op 2 zodat er maar 2 NPCs in elke richting naar achteren worden geduwd. Maar na al deze oplossingen had ik alsnog NPCs die in elkaar konden staan.
```java
private boolean checkCollision(double deltaTime, int iterationAmount) {
        boolean hasCollision = false;
        for (NPC npc : this.collisionNPCs) {
            if (npc.isHome) {
                continue;
            }

            if (npc != this) {
                double thereSize = npc.getHitBoxSize();
                if (npc.getPosition().distanceSq(this.position) < this.hitBoxSize * thereSize + 10) {
                    hasCollision = true;
                    double angleToNPC = Math.atan2(
                            npc.getPosition().getY() - this.position.getY(),
                            npc.getPosition().getX() - this.position.getX());
                    Point2D nextPos = new Point2D.Double(
                            this.position.getX() - (this.speed / 2) * Math.cos(angleToNPC) * deltaTime,
                            this.position.getY() - (this.speed / 2) * Math.sin(angleToNPC) * deltaTime);

                    this.updatePosition(nextPos);

                    if (iterationAmount > 0) {
                        npc.pushPositionBack(Math.atan2(
                                this.position.getY() - npc.getPosition().getY(),
                                this.position.getX() - npc.getPosition().getX()) + 3
                                , deltaTime, iterationAmount);
                    }
                }
            }
        }
        return hasCollision;
    }
    
    public void pushPositionBack(double direction, double deltaTime, int iterationAmount) {
        iterationAmount--;
        Point2D nextPos = new Point2D.Double(
                this.position.getX() - (this.speed / 2) * Math.cos(direction) * deltaTime,
                this.position.getY() - (this.speed / 2) * Math.sin(direction) * deltaTime);
        this.updatePosition(nextPos);
        checkCollision(deltaTime, iterationAmount);
    }
```

Het laatste groote probleem dat ik had gevonden was dat NPCs geen collision checks deden zodra ze still stonden.
Dit maakte het zo dat zodra een NPC een NPC duwde die weer een stilstaande NPC duwde een NPC in een andere kon duwen zonder dat er Enige collision werdt gecheckt.
```java
if (this.target.hasArrived(new Point((int) this.position.getX() / 32, (int) this.position.getY() / 32))) {
            this.standStill = true;
            this.hasArrived();
            
            //solved by adding this line under
            checkCollision(deltaTime, 2);
            return;
        }
```
Dit gigantische probleem was opgelost door die ene regel onder de comment toe te voegen. En daarna was NPC zo ver af als ik het zou krijgen.
Op mijn pc kan ik 200 NPCs aan met volle 60fps op 20x time. 2000 NPCs 1x time met 10fps. 
Die 10fps was nog hoger in mijn test klassen voordat alles werdt samengevoegd. Ik ben dus best wel trots op de NPC klassen.

##Reflectie op JSON
Ik heb gekozen voor de stelling "In het bedrijfsleven wordt steeds meer in software gesimuleerd".  
Ik wist al een beetje van dit onderwerp af omdat ik veel intresse heb in VR en een van de hoofd functies van VR gaat zijn dat je gevaarlijke situaties kan simuleren.
Maar ik wist nog niet zo veel dus heb eerst op moeten zoeken wat software simulatie inhoud.

Waar ik achter was gekomen is dat deze simulaties veel voordelen hebben. Ze zorgen er voor dat projecten tijdens het disignen al getest kunnen worden.
Een disigner kan al test uitvoeren tijdens het ontwerpen en kan daardoor al problemen van te voren oplossen. 
Tijdens het ontwerp kan alles ook geoptimaliseerder gemaakt worden. Je weet toch van tevoren al dat somige features te groot of te klein zijn.
Dat heb je in je simulatie getest.

Het voorkomt dus heel veel risicos en maakt het ontwerpen makelijker. Dit zorgdt ervoor dat dat de kosten van een project verlaagd wordt.
Het aanshaffen/maken van een simulatie zal ten eerste iets kosten. Maar de koste die de simulatie bespaard zal het al snel waard zijn.

Mijn mening op deze stelling is dan ook dat het een zeer positieve beweging is. 
En ik ben blij dat zo veel bedrijven het al gebruiken. Al kunnen altijd meer bedrijven het gebruiken.

##Aplicaties die JSON gebruiken
1. MineCraft
2. Tiled
3. excel

(zoals je merkt kwam ik weer in tijdnoot en heb ik de laatste 2 bladzijdens niet zo netjes kunnen maken)

##Bronnen
https://www.goodfirms.co/blog/the-top-8-free-and-open-source-simulation-software   
https://blog.designsolutions.nl/hoe-kan-simulatie-software-bijdragen-aan-betere-innovaties