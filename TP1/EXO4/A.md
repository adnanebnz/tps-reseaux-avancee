 La classe A contient une variable statique var initialisée à 1.
La méthode main lit le fichier "./EXO4/symptomes.txt" ligne par ligne en utilisant un objet Scanner et appelle la méthode traiter pour chaque ligne lue.
La méthode traiter prend une ligne de texte en entrée, la divise en mots en utilisant StringTokenizer et appelle la méthode Ajouter pour chaque mot rencontré.
La méthode Ajouter prend un mot et une référence vers une Map. Elle vérifie si le mot existe déjà dans la Map. Si le mot n'existe pas, il est ajouté à la Map avec un compteur initialisé à var (qui est 1). Si le mot existe déjà, le compteur est mis à jour en incrémentant sa valeur de 1.
 
 
 ce code compte combien de fois chaque mot apparaît dans un fichier texte et stocke ces informations dans une Map où les clés sont les mots et les valeurs sont les nombres de fois que ces mots apparaissent.