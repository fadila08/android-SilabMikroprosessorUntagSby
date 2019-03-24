# Information System of Laboratory of Microprosessor Untag Surabaya

<p align="center">
  <img width="250" src="images/labmikro.png" title="Logo of Laboratory of Microprosessor Untag Surabaya">
</p>

|Task Name|Description|
|:---|:---|
|`Tables Release`|1.0 |
|`Document Status`|DRAFT |
|`Document Owner`|Nurul Fadhilah|
|`Mockup Designer`|Nurul Fadhilah|
|`Layout Designer`|[Resha Pratama Nugroho](https://web.facebook.com/reshapratama.nugroho)|
|`Developer`|Nurul Fadhilah & [Resha Pratama Nugroho](https://web.facebook.com/reshapratama.nugroho)| 

  
# Background
<p align="justify">
We all know Android app now is on the rise. Nowadays almost everyone already uses a smartphone to help each of its activities. 
No exception to get an information, they just turn the smartphone on and open an app that they want. Therefore we make an information system android platform that aims to make it easy for everyone in University of 17 Agustus 1945 environment, especially for lecturers supervisor, head of laboratory, laboratory officers, laboratory assistant, and students to make it easier to manage things relating to the activities of the laboratory course. So this is something we need to have.
</p>

# Requirements
|No.|User Story Title|User Story Description|Priority|Notes|
|:---|:---|:---|:---|:---|
|`1`|Registered Account|<p align="justify">The participant is active as a student majoring in informatic engineering and signed up at the associated lab course</p>|Must have|The registered account can be either Email addresses or number Whatsapp|
|`2`|Email Integration|<p align="justify">When user forget the password for login, they can receive new pass via Email.</p>|Optional|User need to input the correct Email address in app|
|`3`|Whatsapp Integration|<p align="justify">When user forget the password for login, they can receive new pass via Whatsapp.</p>|Optional|User need to input the correct number of Whatsapp in app|
|`4`|API|<p align="justify">A developer wants to integrate with the mobile app so that they can embed the activity stream on their website</p>|Should have|<ul><li>We should contact to our team as we did something similar</li></ul>

# User Interaction and Design
|No|Name|Image Mockup|Description|
|:---:|:---:|:---:|:---:|
|`1`|Home Screen|<img src="images/homescreen.jpeg" width=250>|<p align="justify">Initial display after splashscreen. There is a menu option such as Login, Profile, Announcements, Downloads, and a Gallery.</p>|
|`2`|Login Screen|<img src="images/login.jpeg" width=250>|<p align="justify">The user must enter a username and password to login to the next page. When user forget the password, then the user will be connected to a different page</p>|
|`3`|Profil Screen|<img src="images/profil.jpeg" width=250>|<p align="justify">Contains the information of laboratory and each officer profile laboratories. Such as head of the laboratory, Laboratory Assistant, Laboran.</p>|
|`4`|StrukturOrg Screen|<img src="images/strukturorg.jpeg" width=250>|<p align="justify">Showing organizational structure of Laboratory of Microprosessor Untag Surabaya</p>|
|`5`|Pengumuman Screen|<img src="images/pengumuman.jpg" width=250>|<p align="justify">Contains RecyclerView that contains the information about announcement. Laboratory Officer (Head of the laboratory and Lab assistant) who made the announcement and then upload it on the system</p>|
|`6`|Unduhan Screen|<img src="images/unduhan.jpg" width=250>|<p align="justify">Contains RecyclerView that contains information about the downloadable things (such as practical task, schedule of implementing the practical, schedule of practical exam) by users of the application, specifically for student</p>|
|`7`|Galeri Screen|<img src="images/galeri.jpg" width=250>|<p align="justify">Gallery containing photos of the ongoing practical activities and user can enjoy the pictures more clearly by clicking one of the photo, and then will appear a pop-up that displays the photo becomes larger.</p>|
