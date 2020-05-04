# UrlshortnerApi - An urlshortening REST API  

# Description
 **Urlshortner API** is a _REST API_ allowing the shortening of very long URLs in order to "mask" the many parameters that may occur in the original URL

# Getting Started
The minimal operations needed to setup your own urlshrtapi installation are the following
`

$ git clone https://github.com/csipiemonte/urlshrtapi

$ cd urlshrtapi

Under the docs/db folder you will find the scripts for the creation of the database schema urlshrtapi relies on.
Replace the token @@user.name@@ with the user_name according to your database user/schema
Execute them on your postgresql database.

$ cd conf/ds

edit the urlshrtapi-ds.xml according to you database information

$ mvn clean package

# Prerequisites
- A [Postgresql](https://www.postgresql.org/) user/schema with CREATE/INSERT/UPDATE/DELETE privileges
- An instance of [Redhat JBOSS EAP 6.4](https://developers.redhat.com/products/eap/download)
- A working Maven runtime

# Deployment
Just deploy the content of the tar file created under the target folder on your JBOSS EAP instance/partition

# Usage

The API exposes two resources:

 - GET /urlshrtapi/{id} : che permette di invocare una url accorciata
      attraverso l'id assegnato dall'operazione di shortening

 - GET /urlshrtapi/?url={url\_originale} : che permette di accorciare la
      url\_originale passata in input ed ottenere la url accorciata
      corrispondente


      Es:  `GET /us?url=http://www.csipiemonte.it HTTP/1.1
              Host: www.url-s.it
              HTTP/1.1 200 OK
              Server: Apache-Coyote/1.1
              Access-Control-Allow-Origin: \*
              Access-Control-Allow-Methods: GET, POST, PUT, DELETE, HEAD
              Access-Control-Allow-Headers: origin, content-type, accept, authorization
              Access-Control-Allow-Credentials: true
              Content-Type: text/plain
              Content-Length: 46
              Date: Thu, 30 Apr 2020 09:42:53 GMT
	          {"shorturl":"http://www.url-s.it/us/822d93c2"}`

# Contributing
Please read CONTRIBUTING.md for details on our code of conduct, and the process for submitting pull requests to us.

# Versioning (Mandatory)
We use Semantic Versioning for versioning. (http://semver.org)

# Authors
See the list of contributors who participated in this project in file AUTHORS.txt.

#Copyrights
See the list of copyrighters in this project in file Copyrights.txt or put here the list of names and years in the form of “© Copyright name – year(s)”.

# License
Licensed under the EUPL-1.2-or-later. See the LICENSE.txt file for details