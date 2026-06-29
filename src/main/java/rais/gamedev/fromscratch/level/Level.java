package rais.gamedev.fromscratch.level;

import rais.gamedev.fromscratch.entity.Entity;
import rais.gamedev.fromscratch.graphics.Screen;
import rais.gamedev.fromscratch.level.tile.Tile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Level {

    public static final int TILE_SIZE = 16;
    public static final int TILE_SIZE_SHIFTING = (int) Math.sqrt(TILE_SIZE);
    public int width, height;
    protected int[] baseLayerTiles;
    protected int[] secondLayerTiles;
    public List<Entity> entities = new ArrayList<>();

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.baseLayerTiles = new int[width *  height];
        generateLevel();
    }

    public Level(String path) {
        loadLevelFromFile(path);
    }

    protected void loadLevelFromFile(String path) {
    }

    protected void generateLevel() {
    }

    // updates the level based on entity changes ( creatures .. )
    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    protected void time() {

    }

    // renders the level
    public void render(int xScroll, int yScroll, Screen screen) {
        // setting the movement offset
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> Level.TILE_SIZE_SHIFTING; // divide xScroll by 2^4 = 16
        // (x1 + 1) tile to be rendered to ensure smooth scrolling
        int x1 = ((xScroll + screen.width) >> Level.TILE_SIZE_SHIFTING) + 1;
        int y0 = yScroll >> Level.TILE_SIZE_SHIFTING;
        int y1 = ((yScroll + screen.height) >> Level.TILE_SIZE_SHIFTING) + 1;
        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).render(x, y, screen);
                if (getSecondLayerTile(x,y) != null)
                    getSecondLayerTile(x,y).render(x,y,screen);
            }
        }
        for (Entity entity : entities) {
            entity.render(screen);
        }
    }

    public void add(Entity entity) {
        entities.add(entity);
    }

    public Tile getTile(int x, int y) {
        return null;
    }

    public Tile getSecondLayerTile(int x, int y) {
        return null;
    }

    /**
     * Calcule un chemin entre (startX, startY) et (targetX, targetY) en coordonnées de TUILES.
     * @return Une liste de nœuds représentant le chemin, ou null si aucun chemin n'est trouvé.
     */
    public List<Node> findPath(int startX, int startY, int targetX, int targetY) {
        // 1. Validation de base : si la cible est un obstacle, impossible d'y aller

        if (isTileSolid(targetX, targetY)) return null;

        // Open List triée automatiquement par le coût 'f' le plus bas
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingDouble(n -> n.f));
        // Closed List pour marquer les tuiles déjà visitées (index unique = x + y * width)
        boolean[] closedList = new boolean[width * height];

        // Nœud de départ
        Node startNode = new Node(startX, startY, null, 0, getDistance(startX, startY, targetX, targetY));
        openList.add(startNode);

        while (!openList.isEmpty()) {
            // On récupère le nœud avec le F le plus bas
            Node current = openList.poll();
            int currentIndex = current.x + current.y * width;

            // Si ce nœud est déjà visité, on passe
            if (closedList[currentIndex]) continue;
            closedList[currentIndex] = true;

            // Si on a atteint la cible, on reconstruit le chemin
            if (current.x == targetX && current.y == targetY) {
                return calculatePath(current);
            }

            // Analyser les 4 voisins (Haut, Bas, Gauche, Droite)
            // (Tu peux ajouter les diagonales si ton jeu le permet)
            int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
            for (int[] dir : directions) {
                int neighborX = current.x + dir[0];
                int neighborY = current.y + dir[1];

                // Vérifier les limites de la map et les collisions
                if (neighborX < 0 || neighborX >= width || neighborY < 0 || neighborY >= height) continue;
                if (isTileSolid(neighborX, neighborY)) continue;

                int neighborIndex = neighborX + neighborY * width;
                if (closedList[neighborIndex]) continue;

                double tentativeG = current.g + 1; // Coût de 1 pour se déplacer d'une tuile
                double h = getDistance(neighborX, neighborY, targetX, targetY);

                // Vérifier si ce chemin vers le voisin est meilleur ou si le voisin n'est pas dans l'openList
                // Pour simplifier et optimiser, on l'ajoute directement s'il n'est pas fermé
                Node neighbor = new Node(neighborX, neighborY, current, tentativeG, h);
                openList.add(neighbor);
            }
        }
        return null; // Aucun chemin trouvé
    }

    /**
     * Reconstruit le chemin du départ à l'arrivée en remontant les parents.
     */
    private List<Node> calculatePath(Node targetNode) {
        List<Node> path = new ArrayList<>();
        Node current = targetNode;
        while (current != null) {
            path.add(0, current); // Ajoute au début pour avoir le chemin dans le bon ordre
            current = current.parent;
        }
        return path;
    }

    /**
     * Heuristique de distance (Distance de Manhattan ou Euclidienne).
     */
    private double getDistance(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy); // Distance Euclidienne
    }

    /**
     * Centralise la vérification de collision d'une tuile (Base layer + Second layer)
     */
    public boolean isTileSolid(int x, int y) {
        Tile baseTile = getTile(x, y);
        Tile secondTile = getSecondLayerTile(x, y);

        // Supposons que ta classe Tile a une méthode tile.isSolid() ou tile.hasCollision()
        if (baseTile != null && getTile(x,y).solid()) return true;
//        if (secondTile != null && getSecondLayerTile(x,y).solid()) return true;

        return false;
    }

    public void logPath(List<Node> path, int startX, int startY, int targetX, int targetY) {
        if (path == null) {
            System.out.println("[PATH] Aucun chemin trouvé entre (" + startX + "," + startY + ") et (" + targetX + "," + targetY + ")");
            return;
        }

        System.out.println("--- NOUVEAU CHEMIN CALCULÉ ---");
        System.out.println("Départ : (" + startX + "," + startY + ") -> Cible : (" + targetX + "," + targetY + ")");
        System.out.println("Nombre de tuiles à parcourir : " + path.size());

        for (int i = 0; i < path.size(); i++) {
            Node n = path.get(i);
            System.out.println("  Étape " + i + " : Tuile(" + n.x + ", " + n.y + ") | Pixels attendus(" + (n.x << 4) + ", " + (n.y << 4) + ")");
        }
        System.out.println("--------------------------------");
    }

}
