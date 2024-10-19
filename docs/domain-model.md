# Domain Model

```plantuml
@startuml
skinparam defaultFontName Fira Code, Monospaced
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

@enduml
```