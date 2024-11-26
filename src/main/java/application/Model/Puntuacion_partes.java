package application.Model;

import javax.persistence.*;

/*
DROP TABLE IF EXISTS `gestionpartes`.`puntuacion_partes`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`puntuacion_partes` (
  `id_punt_partes` INT NOT NULL AUTO_INCREMENT,
  `puntos` INT NOT NULL,
  `tipo_parte` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_punt_partes`),
  UNIQUE INDEX `UK_6m0adpsgoo0hnptrfpdvcx8vm` (`tipo_parte` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;
 */
@Entity
@Table(name = "puntuacion_partes")
public class Puntuacion_partes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_punt_partes")
    private int id_punt_partes;

    @Column(name = "puntos")
    private int puntos;

    @Column(name = "tipo_partes")
    private String tipo_partes;

    public Puntuacion_partes() {
    }

    public Puntuacion_partes(int puntos, String tipo_partes) {
        this.puntos = puntos;
        this.tipo_partes = tipo_partes;
    }

    public int getId_punt_partes() {
        return id_punt_partes;
    }

    public void setId_punt_partes(int id_punt_partes) {
        this.id_punt_partes = id_punt_partes;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getTipo_partes() {
        return tipo_partes;
    }

    public void setTipo_partes(String tipo_partes) {
        this.tipo_partes = tipo_partes;
    }

    @Override
    public String toString() {
        return "Puntuacion_partes{" +
                "puntos=" + puntos +
                ", tipo_partes='" + tipo_partes + '\'' +
                '}';
    }
}