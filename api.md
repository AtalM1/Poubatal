API
===

Directory
---------
**Description :** Consultation de la liste des adresses qui match l'adresse en paramètre

**Param :**
  - address
  - postcode ?
  - oauth

**Return :**
  - success
  - List [id + address]

AddAddress
----------
**Description :** Ajout d'une adresse postale pour la relève des ordures

**Param :**
  - idAddress
  - oauth

**Return :**
  - success

DeleteAddress
-------------
**Description :** Suppression d'une adresse postale pour la relève des ordures

**Param :**
  - idAddress
  - oauth
  
**Return :**
  - success

NewAccount
----------
**Description :** Création d'un nouveau compte utilisateur

**Param :**
  - oauth
  
**Return :**
  - success

DeleteAccount
-------------
**Description :** Suppression d'un compte utilisateur

**Param :**
  - oauth
  
**Return :**
  - success

AddNotif
--------
**Description :** Ajout d'une adresse de notification (email, Twitter, ...)

**Param :**
  - type
  - value
  - oauth
  
**Return :**
  - success
  - idNotif

DeleteNotif
-----------
**Description :** Suppression d'une adresse de notification (email, Twitter, ...)

**Param :**
  - idNotif
  - oauth
  
**Return :**
  - success

AddressList
-----------
**Description :** Consultation de la liste des adresses postales ajoutées au compte

**Param :**
  - oauth
  
**Return :**
  - success
  - List [id + address]

NotifList
---------
**Description :** Consultation de la liste des adresses de notification ajoutées au compte

**Param :**
  - oauth
  
**Return :**
  - success
  - List [id + notif]
