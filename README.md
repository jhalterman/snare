# Snare

*For snaring rabbits and such*

A simple tool for consuming messages from a RabbitMQ queue.

## Setup

Compile it:

```
lein uberjar
```

## Usage

Run it:

```
java -jar target/snare-0.1.0-SNAPSHOT-standalone.jar
```

Supported arguments:

```
 Switches          Default  Desc                          
 --------          -------  ----                          
 -h, --host                 Host                          
 --port            5672     Port                          
 -u, --username             Username                      
 -p, --password             Password                      
 -v, --vhost       /        Vhost                         
 -r, --routingKey  #        Routing key to bind to queue  
 -q, --queue                Name of queue to consume from 
```

## License

Copyright © 2013 Jonathan Halterman

Distributed under the Eclipse Public License, the same as Clojure.
