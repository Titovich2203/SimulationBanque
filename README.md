<div id="top"></div>


<!-- PROJECT SHIELDS -->


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="">
    <img src="https://logos-marques.com/wp-content/uploads/2021/03/Java-Logo.png" alt="Logo" width="200" >
    </a>

<h3 align="center">Projet de simulation de banque</h3>

</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table des matières</summary>
  <ol>
    <li>
      <a href="#about-the-project">A propos du projet</a>
    </li>
        <li><a href="#built-with">Technologies utilisées</a></li>
    <li>
      <a href="#getting-started">Préréquis du projet</a>
      <ul>
        <li><a href="#Préréquis">Préréquis</a></li>
        <li><a href="#installation">Lancement</a></li>
      </ul>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->

## A propos du projet :smile:

Il s’agit de simuler le fonctionnement d’une banque contenant un nombre fixé de caissiers, et recevant des clients qui
arrivent de manière pseudo-aléatoire. Lorsqu’un client arrive, si un caissier est libre il prend en charge le client,
sinon le client prend place dans une file d’attente (supposée commune à tous les caissiers, représentée par exemple par
un syst`eme de tickets numérotés en ordre croissant).

Le but de la simulation est de fournir des résultats statistiques sur les différents acteurs de la simulation. Pour
cela, on donne en entrée de la simulation:

* La `durée estimée de la simulation` : c’est la durée au bout de laquelle la banque n’accepte plus de nouveaux clients.
  Bien entendu, ceux qui se trouvent déjà dans la file seront servis...
* Le `nombre de caissiers`.
* Le `temps moyen de service` de chaque caissier (on suppose donc qu’ils ne sont pas tous également performants ...).
* Le `temps moyen` entre deux arrivées successives de clients.

<div style="text-align: center"><img src="https://i.ibb.co/2PnznV6/image-1.png" alt="Image" ></div><br>
<div style="text-align: center"><img src="https://i.ibb.co/f1z8DKc/image-2.png" alt="Image" ></div><br>
<div style="text-align: center"><img src="https://i.ibb.co/8KmkDBM/image-3.png" alt="Image" ></div><br>
<div style="text-align: center"><img src="https://i.ibb.co/Y0HwW52/image-4.png" alt="Image" ></div><br>
<div style="text-align: center"><img src="https://i.ibb.co/J3k9kxh/image-5.png" alt="Image" ></div><br>
<br><br>

On souhaite obtenir, à la fin de la simulation, les résultats suivants :

* La durée réelle de la simulation (c’est-à-dire la durée au bout de laquelle tous les clients qui se trouvaient encore
  dans la file ont été servis).
* Les longueurs maximale et moyenne de la file d’attente.
* Le nombre de clients servis (au total, et par caissier).
* Le taux d’occupation de chque caissier.
* Le temps moyen d’attente d’un client dans la file
<br><br>
<div style="text-align: center"><img src="https://i.ibb.co/2WfDKxZ/image-6.png" alt="Image" ></div><br><br>
<div style="text-align: center"><img src="https://i.ibb.co/LR9MmRz/image-7.png" alt="Image" ></div><br>

<p align="right">(<a href="#top">back to top</a>)</p>

## Technologies utilisées

This section should list any major frameworks/libraries used to bootstrap your project. Leave any add-ons/plugins for
the acknowledgements section. Here are a few examples.

* [Java](https://www.java.com/fr/)
* [Fxml](https://docs.oracle.com/javafx/2/get_started/fxml_tutorial.htm)
* [Java Fx](https://openjfx.io/)
* [SceneBuilder](https://gluonhq.com/products/scene-builder/)
* [FontAwesome](https://fontawesome.com/)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- Préréquis du projet -->

## Préréquis du projet

Les préréquis de ce projet sont plutôt minimes

### Préréquis

Il nous faudra :

* JDK
  ```sh
  jdk1.8.0_231
  ```

    <table class="otable-w2">
        <thead> 
            <tr><th class="otable-col-head">
                <h3>Product / File Description</h3>
            </th> 
            <th class="otable-col-head">
                <h3>File Size</h3>
            </th> 
            <th class="otable-col-head">
                <h3>Download</h3>
            </th>  
        </tr></thead>
        <tbody>
    <tr>    <td>Linux x86</td>  <td>67.52 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-linux-i586.rpm" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-linux-i586.rpm" data-trackas="lightbox">jre-8u231-linux-i586.rpm</a></div></td>    </tr>
    <tr>    <td> Linux x86</td> <td>83.26 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-linux-i586.tar.gz" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-linux-i586.tar.gz" data-trackas="lightbox">jre-8u231-linux-i586.tar.gz</a></div></td>  </tr>
    <tr>    <td> Linux x64</td> <td>66.62 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-linux-x64.rpm" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-linux-x64.rpm" data-trackas="lightbox">jre-8u231-linux-x64.rpm</a></div></td>  </tr>
    <tr>    <td> Linux x64</td> <td>82.44 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-linux-x64.tar.gz" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-linux-x64.tar.gz" data-trackas="lightbox">jre-8u231-linux-x64.tar.gz</a></div></td>    </tr>
    <tr>    <td> Mac OS X x64</td>  <td>79.91 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-macosx-x64.dmg" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-macosx-x64.dmg" data-trackas="lightbox">jre-8u231-macosx-x64.dmg</a></div></td>    </tr>
    <tr>    <td> Mac OS X x64</td>  <td>71.46 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-macosx-x64.tar.gz" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-macosx-x64.tar.gz" data-trackas="lightbox">jre-8u231-macosx-x64.tar.gz</a></div></td>  </tr>
    <tr>    <td> Solaris SPARC 64-bit</td>  <td>52.15 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-solaris-sparcv9.tar.gz" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-solaris-sparcv9.tar.gz" data-trackas="lightbox">jre-8u231-solaris-sparcv9.tar.gz</a></div></td>    </tr>
    <tr>    <td> Solaris x64</td>   <td>49.97 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-solaris-x64.tar.gz" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-solaris-x64.tar.gz" data-trackas="lightbox">jre-8u231-solaris-x64.tar.gz</a></div></td>    </tr>
    <tr>    <td> Windows x86 Online</td>    <td>1.97 MB  </td>  <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-windows-i586-iftw.exe" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-windows-i586-iftw.exe" data-trackas="lightbox">jre-8u231-windows-i586-iftw.exe</a></div></td>  </tr>
    <tr>    <td> Windows x86 Offline</td>   <td>64.93 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-windows-i586.exe" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-windows-i586.exe" data-trackas="lightbox">jre-8u231-windows-i586.exe</a></div></td>    </tr>
    <tr>    <td> Windows x86</td>   <td>67.39 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-windows-i586.tar.gz" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-windows-i586.tar.gz" data-trackas="lightbox">jre-8u231-windows-i586.tar.gz</a></div></td>  </tr>
    <tr>    <td> Windows x64</td>   <td>72.8 MB  </td>  <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-windows-x64.exe" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-windows-x64.exe" data-trackas="lightbox">jre-8u231-windows-x64.exe</a></div></td>  </tr>
    <tr>    <td> Windows x64</td>   <td>72.45 MB  </td> <td><div class="cb133-download"><a class="license-link icn-download-locked" data-file="//download.oracle.com/otn/java/jdk/8u231-b11/5b13a193868b4bf28bcb45c792fce896/jre-8u231-windows-x64.tar.gz" data-license="141" href="#license-lightbox" rel="lightbox" data-theme="light" data-ltbxclass="license-lightbox" data-lbl="lightbox-open-jre-8u231-windows-x64.tar.gz" data-trackas="lightbox">jre-8u231-windows-x64.tar.gz</a></div></td>    </tr>

     </tbody>
    </table>
  <br><br>

* INTELLIJ (ou un autre IDE)
  <br><br>

  [Cliquez ici pour l'avoir](https://www.jetbrains.com/fr-fr/idea/download/)

### Lancement

Une fois ouvert ouvert dans votre IDE.

1. Assurez vous que le JDK est bien défini sur le nouveau installé
2. Le fichier de démarrage du pojet est:
   ```sh
   src/sample/Main.java
   ```
3. Veuillez stipulez le fichier de démarrage
4. Faites vos simulations en toute quiétude :smile: !!!!

<p align="right">(<a href="#top">back to top</a>)</p>

