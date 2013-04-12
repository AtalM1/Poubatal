Documentation de l'API Poubatal
===

Chaque service est accessible via l'URL http://poubatal.appspot.com/api/{service}. Suivant la méthode HTTP, le service réagit différement.
Lorsque un service renvoi des informations, elles sont toujours structurées en JSON de la manière suivante :
```json
{
  "data":[
    {element1},
    {element2},
    {etc...}
  ]
}
```
Dans la documentation qui suit, seul le détail des éléments est donnée.

Directory
---------
###Méthode GET
**Description :** Consultation de la liste des adresses disponible sur l'OpenData de Nantes

**Retour :**
  - id : string
  - streetName : string
  - numberDescription : string
  - zoneName : string
  - city : string
  - mixte : boolean
  - yellowBin : tableau<string>
  - blueBin : tableau<string>

**Erreurs :**
  - 204 No content : Si l'OpenData de Nantes ne répond pas
  
Account
-------
###Méthode DELETE
**Description :** Suppression du compte d'un utilisateur

**Paramètres :**
  - oauth : L'access token du compte Google
  
**Erreurs :**
  - 401 Unauthorized : Si il manque le paramètre 'oauth', ou si il est erroné
  - 404 Not found : Si le compte de l'utilisateur n'existe pas

Address
-------
###Méthode GET
**Description :** Récupération des adresses enregistrées sur le compte d'un utilisateur

**Paramètres :**
  - oauth : L'access token du compte Google

**Retour :**
  - id : string
  - streetName : string
  - numberDescription : string
  - zoneName : string
  - city : string
  - mixte : boolean
  - yellowBin : tableau<string>
  - blueBin : tableau<string>
  
**Erreurs :**
  - 401 Unauthorized : Si il manque le paramètre 'oauth', ou si il est erroné

###Méthode POST
**Description :** Ajout d'une adresse postale sur le compte d'un utilisateur

**Paramètres :**
  - oauth : L'access token du compte Google
  - address : L'ID de l'adresse récupéré grâce au service Directory

**Erreurs :**
  - 400 Bad request : Si il manque un paramètre
  - 401 Unauthorized : Si il manque le paramètre 'oauth', ou si il est erroné
  - 404 Not found : Si l'adresse envoyée ne correspond à aucune adresse de l'OpenData de Nantes
  - 409 Conflict : Si l'adresse est déjà enregistrée sur le compte de l'utilisateur

###Méthode DELETE
**Description :** Suppression d'une adresse postale enregistrée sur le compte d'un utilisateur

**Paramètres :**
  - oauth : L'access token du compte Google
  - address : L'ID de l'adresse récupéré grâce au service Address - GET

**Erreurs :**
  - 400 Bad request : Si il manque un paramètre
  - 401 Unauthorized : Si il manque le paramètre 'oauth', ou si il est erroné
  - 404 Not found : Si l'adresse demandée ne correspond à aucune adresse de l'OpenData de Nantes, ou si l'adresse n'est pas enregistrée sur le compte de l'utilisateur

Notification
------------
###Méthode GET
**Description :** Récupération des adresses de notifications enregistrées sur le compte d'un utilisateur

**Paramètres :**
  - oauth : L'access token du compte Google

**Retour :**
  - id : string
  - type : string
  - email : string

**Erreurs :**
  - 401 Unauthorized : Si il manque le paramètre 'oauth', ou si il est erroné
  
###Méthode POST
**Description :** Ajout d'une adresse de notification sur le compte d'un utilisateur

**Paramètres :**
  - oauth : L'access token du compte Google
  - type : Le type de notification [email ou xmpp]
  - email : L'adresse email qui doit être notifiée

**Erreurs :**
  - 400 Bad request : Si il manque un paramètre
  - 401 Unauthorized : Si il manque le paramètre 'oauth', ou si il est erroné
  - 409 Conflict : Si l'adresse de notification est déjà enregistrée sur le compte de l'utilisateur

###Méthode DELETE
**Description :** Suppression d'une adresse de notification enregistrée sur le compte d'un utilisateur

**Paramètres :**
  - oauth : L'access token du compte Google
  - notification : L'ID de la notification récupéré grâce au service Notification - GET

**Erreurs :**
  - 400 Bad request : Si il manque un paramètre
  - 401 Unauthorized : Si il manque le paramètre 'oauth', ou si il est erroné
  - 404 Not found : Si la notification demandée ne correspond à aucune adresse de notification enregistrée sur le compte de l'utilisateur
