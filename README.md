Service Me
==========

An attempt at a [Dropwizard](http://www.dropwizard.io/) like framework, where
the underlying server is [Undertow](http://undertow.io/), the code has been
written to avoid the need for any inversion of control container, and most 
annotations.

Lack of IoC Container
---------------------
Requires that the natural concurrency of various sub-systems be
exposed correctly, and crossing various concurrency boundaries, while
maintaining sanity, requires concurrency patterns such as promises, actors, 
etc... Though this might seem like a drawback, easier this than a slew of 
annotations littering code. @Inject, @Context and so on end up everywhere, while
developers wonder why their code does or doesn't work.

Put another way, these containers are hard to reason about. In my experience, 
While others are often putting them in place. When issues around life cycles, or
what have you, pop up I'm also the one who debugs them.

Lack of Annotations
-------------------
Action at a distance, should be kept to a minimum if at all possible. Most of
the annotations in [Dropwizard](http://www.dropwizard.io/) would be rendered
unnecessary if the underlying APIs weren't so hostile to actual dependency
injection (a fancy term of constructor/method/function parameters).

ToDo
====
* Unit test all the things
* Work towards the operational simplicity of Dropwizard
    * Metrics
    * Unified Logging
    * Uber jars
    * Configuration
    * Health checks
* More testing, property based, model based, Pact, etc...
* First class considerations
    * Tracing
    * Bulk heading
    * Favour immutability
    * Hypermedia (JSON API)
    * Websocket support

License
=======

Apache V2