package kdg.be.parchis.model.game;

import java.util.HashMap;
import java.util.Map;

public class CoordinateConverter {
    private Map<Integer, Position> positionMap = new HashMap<>();

    public CoordinateConverter() {
        //NESTS
        //yellow nest
        positionMap.put(69, new Position(160, -150));

        positionMap.put(6901, new Position(160, -150));
        positionMap.put(6902, new Position(185, -150));
        positionMap.put(6903, new Position(210, -150));
        positionMap.put(6904, new Position(235, -150));

        //blue nest
        positionMap.put(70, new Position(-160, -150));

        positionMap.put(7001, new Position(-160, -150));
        positionMap.put(7002, new Position(-185, -150));
        positionMap.put(7003, new Position(-210, -150));
        positionMap.put(7004, new Position(-235, -150));
        //red nest
        positionMap.put(71, new Position(-160, 180));

        positionMap.put(7101, new Position(-160, 180));
        positionMap.put(7102, new Position(-185, 180));
        positionMap.put(7103, new Position(-210, 180));
        positionMap.put(7104, new Position(-235, 180));
        //green nest
        positionMap.put(72, new Position(160, 180));

        positionMap.put(7201, new Position(160, 180));
        positionMap.put(7202, new Position(185, 180));
        positionMap.put(7203, new Position(210, 180));
        positionMap.put(7204, new Position(235, 180));

        //yellow start position
        positionMap.put(5, new Position(170, -40));

        //blue start position
        positionMap.put(22, new Position(-50, -160));

        //red start position
        positionMap.put(39, new Position(-173, 60));

        //green start position
        positionMap.put(56, new Position(45, 185));

        positionMap.put(1, new Position(287, -40));
        positionMap.put(2, new Position(258, -40));
        positionMap.put(3, new Position(230, -40));
        positionMap.put(4, new Position(200, -40));
        positionMap.put(6, new Position(142, -40));
        positionMap.put(7, new Position(112, -40));
        positionMap.put(8, new Position(82, -40));
        positionMap.put(9, new Position(48, -70));
        positionMap.put(10, new Position(48, -105));
        positionMap.put(11, new Position(48, -130));
        positionMap.put(12, new Position(48, -160));
        positionMap.put(13, new Position(48, -188));
        positionMap.put(14, new Position(48, -220));
        positionMap.put(15, new Position(48, -250));
        positionMap.put(16, new Position(48, -277));
        positionMap.put(17, new Position(-20, -277));
        positionMap.put(18, new Position(-50, -277));
        positionMap.put(19, new Position(-50, -250));
        positionMap.put(20, new Position(-50, -220));
        positionMap.put(21, new Position(-50, -190));
        positionMap.put(23, new Position(-50, -130));
        positionMap.put(24, new Position(-50, -105));
        positionMap.put(25, new Position(-50, -70));
        positionMap.put(26, new Position(-85, -35));
        positionMap.put(27, new Position(-115, -35));
        positionMap.put(28, new Position(-145, -35));
        positionMap.put(29, new Position(-173, -35));
        positionMap.put(30, new Position(-202, -35));
        positionMap.put(31, new Position(-232, -35));
        positionMap.put(32, new Position(-260, -35));
        positionMap.put(33, new Position(-290, -35));
        positionMap.put(34, new Position(-290, 28));
        positionMap.put(35, new Position(-290, 60));
        positionMap.put(36, new Position(-260, 60));
        positionMap.put(37, new Position(-232, 60));
        positionMap.put(38, new Position(-202, 60));
        positionMap.put(40, new Position(-145, 60));
        positionMap.put(41, new Position(-115, 60));
        positionMap.put(42, new Position(-85, 60));
        positionMap.put(43, new Position(-50, 100));
        positionMap.put(44, new Position(-50, 125));
        positionMap.put(45, new Position(-50, 155));
        positionMap.put(46, new Position(-50, 183));
        positionMap.put(47, new Position(-50, 210));
        positionMap.put(48, new Position(-50, 240));
        positionMap.put(49, new Position(-50, 270));
        positionMap.put(50, new Position(-50, 300));
        positionMap.put(51, new Position(18, 300));
        positionMap.put(52, new Position(45, 300));
        positionMap.put(53, new Position(45, 270));
        positionMap.put(54, new Position(45, 240));
        positionMap.put(55, new Position(45, 210));
        positionMap.put(57, new Position(45, 155));
        positionMap.put(58, new Position(45, 125));
        positionMap.put(59, new Position(45, 100));
        positionMap.put(60, new Position(82, 60));
        positionMap.put(61, new Position(112, 60));
        positionMap.put(62, new Position(142, 60));
        positionMap.put(63, new Position(170, 60));
        positionMap.put(64, new Position(200, 60));
        positionMap.put(65, new Position(230, 60));
        positionMap.put(66, new Position(258, 60));
        positionMap.put(67, new Position(287, 60));
        positionMap.put(68, new Position(287, 10));

        //yellow landingstrip
        positionMap.put(73, new Position(258, 10));
        positionMap.put(74, new Position(230, 10));
        positionMap.put(75, new Position(200, 10));
        positionMap.put(76, new Position(170, 10));
        positionMap.put(77, new Position(142, 10));
        positionMap.put(78, new Position(112, 10));
        positionMap.put(79, new Position(82, 10));
        positionMap.put(80, new Position(50, 10));

        //blue landingstrip
        positionMap.put(81, new Position(0, -250));
        positionMap.put(82, new Position(0, -220));
        positionMap.put(83, new Position(0, -190));
        positionMap.put(84, new Position(0, -160));
        positionMap.put(85, new Position(0, -130));
        positionMap.put(86, new Position(0, -100));
        positionMap.put(87, new Position(0, -70));
        positionMap.put(88, new Position(0, -40));

        // red landingstrip
        positionMap.put(89, new Position(-260, 10));
        positionMap.put(90, new Position(-230, 10));
        positionMap.put(91, new Position(-200, 10));
        positionMap.put(92, new Position(-170, 10));
        positionMap.put(93, new Position(-145, 10));
        positionMap.put(94, new Position(-115, 10));
        positionMap.put(95, new Position(-85, 10));
        positionMap.put(96, new Position(-45, 10));

        // Green landingstrip
        positionMap.put(97, new Position(0, 270));
        positionMap.put(98, new Position(0, 240));
        positionMap.put(99, new Position(0, 210));
        positionMap.put(100, new Position(0, 180));
        positionMap.put(101, new Position(0, 150));
        positionMap.put(102, new Position(0, 120));
        positionMap.put(103, new Position(0, 90));
        positionMap.put(104, new Position(0, 55));
    }

    public int getX(int tileNr) {
        return positionMap.get(tileNr).getX();
    }

    public int getY(int tileNr) {
        return positionMap.get(tileNr).getY();
    }

    public int getPosition (int x, int y) {
        for (Map.Entry<Integer, Position> entry : positionMap.entrySet()) {
            if (entry.getValue().getX() == x && entry.getValue().getY() == y) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
