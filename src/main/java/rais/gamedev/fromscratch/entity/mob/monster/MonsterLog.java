package rais.gamedev.fromscratch.entity.mob.monster;

import rais.gamedev.fromscratch.Game;
import rais.gamedev.fromscratch.entity.mob.Direction;
import rais.gamedev.fromscratch.entity.mob.Mob;
import rais.gamedev.fromscratch.entity.mob.MonsterState;
import rais.gamedev.fromscratch.entity.projectile.FireBolt;
import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.graphics.sprites.LogMonsterSprite;
import rais.gamedev.fromscratch.graphics.sprites.Sprite;
import rais.gamedev.fromscratch.level.Level;
import rais.gamedev.fromscratch.level.Node;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class MonsterLog extends Mob {
    private Sprite sprite;
    private int animate = 0;
    private boolean walking = false;
    private float wanderTimer = 100;
    private int fireRate; //  represents a gun
    private double aggroDistance = 100;
    private Point currentTargetPoint = new Point(x+1000,y+1000);
    private MonsterState state = MonsterState.IDLE;
    private double attackRange = 10.0;
    private List<Node> path;
    private int timeSinceLastPath = 0;

    public MonsterLog() {
        sprite = LogMonsterSprite.log_monster;
        fireRate = FireBolt.FIRE_RATE;
    }

    public MonsterLog(int x, int y) {
        this.x = x;
        this.y = y;
        spawnPoint = new Point(x, y);
        sprite = LogMonsterSprite.log_monster;
    }

//    public void update() {
//        int xChange = 0, yChange = 0;
//        if (animate < Integer.MAX_VALUE) animate++;
//        else animate = 0;
//        int randomDirection = new Random().nextInt(4);
//        if (Direction.West.ordinal() == randomDirection) xChange--;
//        if (Direction.East.ordinal() == randomDirection) xChange++;
//        if (Direction.North.ordinal() == randomDirection) yChange--;
//        if (Direction.South.ordinal() == randomDirection) yChange++;
//        // Only move if the playerForward actually moved
//        if (xChange != 0 || yChange != 0) {
//            walking = true;
//            move(xChange, yChange);
//        } else walking = false;
//    }

    public void update() {
//        moveTowards(getPlayerPosition(),10.0f);
        switch(state) {
            case IDLE:
                if (seesPlayer()) changeState(MonsterState.CHASE);
                else if (randomChance(0.01)) changeState(MonsterState.WANDER);
                break;

            case WANDER:
                wanderBehavior(10.0f);
                if (seesPlayer()) changeState(MonsterState.CHASE);
                break;

            case CHASE:
                chaseBehavior(20.0f);
                break;

            case ATTACK:
                attackBehavior();
                break;

            case RETURN:
                returnBehavior(20.0f);
                break;
        }
    }

    private boolean randomChance(double v) {
        return  (Math.random() <= v);
    }

    private void changeState(MonsterState state) {
        this.state = state;
    }

    public void render(Screen screen) {
        boolean xFlip = false, yFlip = false;

        if (animate % 20 > 10 && walking) sprite = LogMonsterSprite.log_monsterSide_1;
        else if (!walking) sprite = LogMonsterSprite.log_monsterSide;
        else sprite = LogMonsterSprite.log_monsterSide_2;

        if (direction == Direction.North.ordinal()) {
            if (animate % 20 > 10 && walking) sprite = LogMonsterSprite.log_monsterForward_1;
            else if (!walking) sprite = LogMonsterSprite.log_monster;
            else sprite = LogMonsterSprite.log_monsterForward_2;
        } else if (direction == Direction.South.ordinal()) {
            if (animate % 20 > 10 && walking) sprite = LogMonsterSprite.log_monsterBackward_1;
            else if (!walking) sprite = LogMonsterSprite.log_monsterBackward;
            else sprite = LogMonsterSprite.log_monsterBackward_2;
        } else if (direction == Direction.West.ordinal()) {
            xFlip = true;
        }
        screen.renderMob(x, y, sprite, xFlip, yFlip, 0x0000);
//        for (Projectile projectile: projectiles) {
//            projectile.render(screen);
//        }
    }

    void wanderBehavior(float dt) {
        wanderTimer -= dt;
        if (wanderTimer <= 0 || reached(currentTargetPoint)) {
            currentTargetPoint = randomNearbyPoint(spawnPoint, 5); // radius
            wanderTimer = new Random().nextInt(100); // new goal every 1–3 seconds
        }

        moveTowards(currentTargetPoint,dt);
    }

//    private void moveTowards(Point currentTargetPoint, float dt) {
//        wanderTimer -= dt;
//        if (wanderTimer <= 0 || reached(currentTargetPoint)) {
//            wanderTimer = new Random().nextInt(100); // new goal every 1–3 seconds
//            double dx = currentTargetPoint.getX() - x;
//            double dy = currentTargetPoint.getY() - y;
//            double monsterPlayerAngle = Math.atan2(dy, dx);
//            // moving towards the target by using the angle of the vector between two points
//            // the 1.5 is for adjusting the cast into TODO::probably needs to be fixed by changing the move function from accepting integers to doubles
//            int xChange = (int) (Math.cos(monsterPlayerAngle) * 1.5), yChange = (int) (Math.sin(monsterPlayerAngle) * 1.5);
//            if (animate < Integer.MAX_VALUE) animate++;
//            else animate = 0;
//            // Only move if the playerForward actually moved
//            if (xChange != 0 || yChange != 0) {
//                walking = true;
//                move(xChange, yChange);
//            } else walking = false;
//        }
//    }
    private void moveTowards(Point currentTargetPoint, float dt) {
        wanderTimer -= dt;
        if (wanderTimer <= 0 || reached(currentTargetPoint)) {
            wanderTimer = new Random().nextInt(50); // new goal every 1–3 seconds
            timeSinceLastPath++;
            // Convertir la position en pixels du monstre et du joueur en coordonnées de TUILES
            int monsterTileX = this.x / Level.TILE_SIZE; // Même logique que ton render (x / 16)
            int monsterTileY = this.y / Level.TILE_SIZE;
            int playerTileX = currentTargetPoint.x / Level.TILE_SIZE;
            int playerTileY = currentTargetPoint.y / Level.TILE_SIZE;
            // Recalculer le chemin vers le joueur toutes les 30 frames (0.5s)
            if (timeSinceLastPath >= 20) {
                timeSinceLastPath = 0;



                path = level.findPath(monsterTileX, monsterTileY, playerTileX, playerTileY);
                System.out.println("Monster : " + monsterTileX + " " + monsterTileY + " Player : " + playerTileX + " " + playerTileY);
                level.logPath(path, monsterTileX, monsterTileY, playerTileX, playerTileY); // <--- LIGNE DE LOG

            }

            // Déplacement le long du chemin
            if (path != null && path.size() > 1) {
                Node nextStep = path.get(1); // Le nœud 0 est la position actuelle du monstre

                // Only move if the playerForward actually moved
                if (nextStep.x != 0 || nextStep.y != 0) {
                    walking = true;
                    System.out.println("moving: " + (int) Math.signum(nextStep.x - monsterTileX) + " " + (int) Math.signum(nextStep.y - monsterTileY));
                    System.out.println("moving: " + (nextStep.x - monsterTileX) + " " + (nextStep.y - monsterTileY));
                    System.out.println("x : " + nextStep.x + " y : " + nextStep.y);
                    System.out.println("x : " + monsterTileX + " y : " + monsterTileY);
                    move((int) Math.signum(nextStep.x - monsterTileX), (int) Math.signum(nextStep.y - monsterTileY));
                } else walking = false;
            }

        }
    }

    private Point randomNearbyPoint(Point spawnPoint, int i) {
        int random = new Random().nextInt(4);
        if (random == 0) return new  Point(spawnPoint.x + i, spawnPoint.y + i);
        if (random == 1) return new Point(spawnPoint.x + i, spawnPoint.y - i);
        if (random ==  2) return new Point(spawnPoint.x - i, spawnPoint.y + i);
        return new Point(spawnPoint.x - i, spawnPoint.y - i);
    }

    private boolean reached(Point currentTargetPoint) {
        return this.x == currentTargetPoint.x && this.y == currentTargetPoint.y;
    }

    private boolean seesPlayer() {
        return distanceToPlayer() <= aggroDistance;
    }

    private double distanceToPlayer(){
        return Math.sqrt((this.x - Game.player.x)*(this.x - Game.player.x) + (this.y - Game.player.y)*(this.y - Game.player.y));
    }

    private Point getPlayerPosition() {
        return new Point(Game.player.x, Game.player.y);
    }

    void chaseBehavior(float dt) {
        moveTowards(getPlayerPosition(),dt);

        if (distanceToPlayer() < attackRange)
            changeState(MonsterState.ATTACK);

        if (!seesPlayer())
            changeState(MonsterState.RETURN);
    }

    void attackBehavior() {
        if (distanceToPlayer() > attackRange) {
            changeState(MonsterState.CHASE);
            return;
        }

//        if (attackCooldown <= 0) {
//            player.takeDamage(damage);
//            attackCooldown = attackSpeed; // seconds
//        }
    }

    void returnBehavior(float dt) {
        moveTowards(spawnPoint,dt);

        //if (new Point(x,y).distance(spawnPoint) < 5)
            changeState(MonsterState.IDLE);
    }


}
