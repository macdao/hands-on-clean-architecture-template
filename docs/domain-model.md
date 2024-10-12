# Domain Model

```plantuml
skinparam defaultFontName Fira Code, Monospaced
skinparam handwritten true

hide empty members

entity User  {

}

entity Order {
    status
    price
}

enum OrderStatus
Order *- OrderStatus
User "1" -- "*" Order: place >
```