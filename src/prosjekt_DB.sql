/*
kardinalitetene?
Kardinalitetene representeres ved bruk av en assosiativ tabell
som har en kompositt nøkkel som består av to fremmednøkler
disse fremmednøklene referer til hver sin tabell som da
utgjørn en kardinal/relasjon

subklassene blir representert som egene tabeller
med en primær nøkkel som også er en fremmednøkel.
Denne nøkkelen refererer til super tabellen
*/

-- Tabell for treningsøkt
CREATE TABLE `treningsøkt` (
  `id` INT UNSIGNED NOT NULL,
  `dato` DATETIME NULL,
  `varighet` TIME NULL,
  `prestasjon` INT(2) NULL,
  `notat` VARCHAR(256) NULL,
  `form` INT(2) NULL,
  PRIMARY KEY (`id`));


-- Tabell for øving
CREATE TABLE `øving` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `navn` VARCHAR(45) NULL,
  `beskrivelse` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

/*
Tabell som representerer kardinalen mellom treningsøkt og øving
Én treningsøkt kan ha flere øvinger
*/
CREATE TABLE `treningsøkt_har_øving` (
  `id_trening` INT UNSIGNED NOT NULL,
  `id_øving` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_trening`, `id_øving`),
  INDEX `id_øving_fk_idx` (`id_øving` ASC),
  CONSTRAINT `id_treningsøkt_fk`
    FOREIGN KEY (`id_trening`)
    REFERENCES `db_prosjekt`.`treningsøkt` (`id`),
  CONSTRAINT `id_øving_fk`
    FOREIGN KEY (`id_øving`)
    REFERENCES `db_prosjekt`.`øving` (`id`));

/*
Styrke øving er en subklasse av øving
Som representeres ved at primær nøkkelen
referer til én øving i øvings tabellen
*/
CREATE TABLE `styrke_kond_øvelse` (
  `id_øvelse` INT UNSIGNED NOT NULL,
  `belastning` INT NULL,
  `type` INT NULL,
  `repitisjoner` INT NULL,
  `sett` INT NULL,
  PRIMARY KEY (`id_øvelse`),
  CONSTRAINT `styrke_øvelse_fk`
    FOREIGN KEY (`id_øvelse`)
    REFERENCES `øving` (`id`));

/*
Utholdenhetsøvelse er en subklasse av øving
Som representeres ved at primær nøkkelen
referer til én øving i øvings tabellen
*/
CREATE TABLE `utholdenhet_øvelse` (
  `id_øvelse` INT UNSIGNED NOT NULL,
  `distanse_km` DOUBLE NULL,
  `tid_min` DOUBLE NULL,
  PRIMARY KEY (`id_øvelse`),
  CONSTRAINT `utholdenhet_ovelse_fk`
    FOREIGN KEY (`id_øvelse`)
    REFERENCES `øving` (`id`));

/*
En kategori, for å representere
subkategorier bruker vi en parent attributt
*/
CREATE TABLE `kategori` (
  `id` INT UNSIGNED NOT NULL,
  `navn` VARCHAR(45) NULL,
  `parent` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `kategori_parent_fk`
    FOREIGN KEY (`parent`)
    REFERENCES `kategori` (`id`));

/*
Hver øvelse kan være en del av flere kategorier
Det er noe enkel måte å kreve at en øving skal 
minst ha en kategori, dette må gjøres via et view
*/
CREATE TABLE `øvelse_har_kategori` (
  `id_øvelse` INT UNSIGNED NOT NULL,
  `id_kategori` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_øvelse`, `id_kategori`),
  INDEX `ok_kategori_fk_idx` (`id_kategori` ASC),
  CONSTRAINT `ok_kategori_fk`
    FOREIGN KEY (`id_kategori`)
    REFERENCES `db_prosjekt`.`kategori` (`id`),
  CONSTRAINT `ok_ovelse_fk`
    FOREIGN KEY (`id_øvelse`)
    REFERENCES `øving` (`id`));

/*
utendørsaktivitet er en subklasse av treningsøkt
dette representeres ved at primærnøkkelen refererer
til én treningsøkt
*/
CREATE TABLE `utendør_aktivitet` (
  `id_treningsøkt` INT UNSIGNED NOT NULL,
  `temperatur` INT NULL,
  `væretype` VARCHAR(45) NULL,
  PRIMARY KEY (`id_treningsøkt`),
  CONSTRAINT `utendør_aktivitet_trening_fk`
    FOREIGN KEY (`id_treningsøkt`)
    REFERENCES `treningsøkt` (`id`));


/*
innendørsaktivitet er en subklasse av treningsøkt
dette representeres ved at primærnøkkelen refererer
til én treningsøkt
nnendørs aktivitet og utendørsaktivitet skal egentlig 
være disjunkte, men det er ingen enkel måte å gjør dette
på i SQL
*/
CREATE TABLE `innendør_aktivitet` (
  `id_treningsøkt` INT UNSIGNED NOT NULL,
  `ventilasjon` INT NULL,
  `antall_tilskuere` INT NULL,
  PRIMARY KEY (`id_treningsøkt`),
  CONSTRAINT `innedør_aktivetet_trening_fk`
    FOREIGN KEY (`id_treningsøkt`)
    REFERENCES `treningsøkt` (`id`));

/*    
Datapunk er en svak identitet som er
koblet opp mot en treningsøkt og implisitt
koblet opp mot en eller flere øvinger
*/
CREATE TABLE `datapunkt` (
  `id` INT UNSIGNED NOT NULL,
  `tid` TIME NULL,
  `puls` INT NULL,
  `lengde_grad` DOUBLE NULL,
  `bredde_grad` DOUBLE NULL,
  `MOH` INT NULL,
  `id_treningsøkt` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`, `id_treningsøkt`),
  CONSTRAINT `datapunkt_trening_fk`
    FOREIGN KEY (`id`)
    REFERENCES `treningsøkt` (`id`));

