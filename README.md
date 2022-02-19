# Compiler-for-microjava
Implemented MicroJava compiler, school project that follows 4 different phases.
<h1> Nacin pokretanja koda: </h1>
<b>1. generisanje leksera</b>
<li>build.xml -> run as -> antbuild -> lexerGen </li>
<li>Faza 1 - izvorni kod kopiran sa sajta se nalazi u zip arhivi</li>
<li>Izmene se rade na mjlexer.lex fajlu</li>
<b>2. Sintaksna analiza</b>
<li>build.xml -> run as -> antbuild -> mjParser</li>
<li>Faza 2- izvorni kod kopiran sa sajta se nalazi u zip arhivi</li>
<li>Izmene se rade na mjparser.cup fajlu, klase se same generisu nakon pokretanja antbuild fajla</li>
<b>3. Semanticka analiza
<li>build.xml -> run as -> antbuild -> mjParser</li>
<li>Faza 2- izvorni kod kopiran sa sajta se nalazi u zip arhivi</li>
<li>Izmene se rade na SemanticPass.java</li>
<b>4. Generisanje koda
<li>build.xml -> run as -> antbuild -> mjParser</li>
<li>Faza 4- izvorni kod kopiran sa sajta se nalazi u zip arhivi</li>
<li>Izmene se rade na SemanticPass.java</li>

