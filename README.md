# snare

*For snaring rabbits and such*

Snare is a simple tool for consuming messages from a RabbitMQ queue.

## Setup

To compile via Leiningen:

```
lein uberjar
```

## Usage

To run Snare:

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

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
