# NexaBank — Application de Gestion Bancaire Digitale

![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Architecture](https://img.shields.io/badge/Architecture-POO-blue.svg)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen.svg)

## 📌 Présentation du projet
**NexaBank** est une application console développée en Java permettant de simuler la gestion de comptes bancaires numériques. Elle offre aux clients la possibilité d'effectuer leurs opérations courantes en autonomie et permet aux gestionnaires de suivre la situation financière des clients.

Ce projet a été réalisé dans le cadre du **Brief 1 — Développement d'une application de gestion bancaire digitale** (Référentiel CDA 2026).

---

## 🚀 Fonctionnalités Principales

### 👤 Espace Client
* **Consultation :** Affichage du solde et détails des comptes (Courant et Épargne).
* **Opérations :**
    * Dépôt d'argent sur un compte.
    * Retrait d'argent sécurisé avec vérification du solde.
    * Virement inter-comptes.
* **Relevé bancaire :** Génération et lecture de l'historique des transactions stocké dans un fichier `.txt` unique par compte.

### 👔 Espace Gestionnaire (Banquier)
* **Gestion des comptes :** Création, modification des informations clients et clôture de comptes.
* **Consultation globale :** Affichage de la liste des clients et accès direct à leurs relevés bancaires.

---

## 🛠️ Architecture Technique

Le projet respecte les principes fondamentaux de la **Programmation Orientée Objet (POO)** et une séparation claire des responsabilités :

```text
src/
├── exception/               # Custom exceptions (SoldeInsuffisant, MontantNegatif, etc.)
├── model/                   # Entités (Personne, Client, Gestionnaire, Compte, Transaction, Enums)
├── services/                # Logique métier (AuthService, ClientService, CompteService, TransactionService)
└── Main.java                # Point d'entrée de l'application (Menu Console)