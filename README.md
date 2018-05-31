# pure-clj Leiningen new template

Leiningen new template for [pure-clj](https://github.com/stackoverflow/pure-clj),
a Clojure backend for Purescript.

## Usage

Leiningen will download the template automatically so there's no need to install anything.

    lein new pure-clj project-name type

There are two types of projects one can create:

- `app` for applications with a main.
- `lib` for libraries. This is the default.

Example:

    # create project
    lein new pure-clj myproject app
    cd myproject
    # install purescript dependencies
    psc-package install
    # compile purescript
    lein pursclj
    lein run
    => "Hello myproject"

## License

Copyright © 2018 Islon Scherer

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
