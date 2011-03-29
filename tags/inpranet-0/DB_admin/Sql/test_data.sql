-- Function: profil.fillchoice()

-- DROP FUNCTION profil.fillchoice();

CREATE OR REPLACE FUNCTION profil.fillchoice()
  RETURNS void AS
$BODY$
DECLARE
	uid integer;
	i integer;
	a boolean;
	b boolean;

BEGIN
	FOR uid IN 1001..1007 loop
		FOR i IN 1..7 loop
			a := trunc(random()*(1+1));
			IF a=TRUE THEN b:=FALSE;
			ELSE b:=TRUE;
			END IF;
				INSERT INTO profil.habit_choice VALUES (uid, i, a, b);
		END loop;
	END loop;
	
END;$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION profil.fillchoice() OWNER TO postgres;
