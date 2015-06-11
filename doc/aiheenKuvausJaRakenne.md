**Aihe:** PongPong on mukaelma klassisesta Pong-pelist‰. PongPong peliss‰ poistetaan alkuper‰isen Pong pelikent‰n yl‰- ja alasein‰t ja niiden tilalle tulee liikuteltavat palkit. N‰inollen pelaaja liikuttaa kahta palkkia (tai mailaa) yhden palkin sijaan tai nelj‰‰ palkkia yksinpelimoodissa.

**K‰ytt‰j‰t:** Peleist‰ kiinnostuneet ihmiset.

**Kaikkien k‰ytt‰jien toiminnot:**
- Valitse pelimuoto
	- Yksi pelaaja itse‰‰n vastaan (Tavoite pit‰‰ pallo kent‰ll‰ mahdollisimman pitk‰‰n)
	- Kaksi pelaajaa toisiaan vastaan (Tavoite saada pallo vastustajan puolustuksen l‰pi)
	- Nelj‰ pelaajaa toisiaan vastaan (Tavoite saada pallo vastustajan puolustuksen l‰pi)

**Pelin rakenne**
Pelin luokat:
- StartMenu luo alkuvalikon jossa valitaan pelaajien m‰‰r‰. StartMenu sis‰lt‰‰ main() metodin. 
- GameCanvas luo pelikent‰n.
- PaddleControl reagoi pelaajien n‰pp‰imistˆlt‰ annettuihin komentoihin ja liikuttaa mailoja vastaavasti.
- Game luo pelaajaoliot ja pallo-oliot. Se myˆs sis‰lt‰‰ metodit tˆrm‰yksien tutkimiseen ja pisteiden laskemiseen.
- Paddle on luokka josta pelaajaoliot muodostetaan.
- Ball on luokka josta pallo-olio muodostetetaan. 

