# Decisions

## 1. Project Template: Strategy

- **SOLID (OCP) compliance:** Promotions should be modular and easily expandable with new promotions, without directly moddifying existing code.
- **Swapability at runtime:** Promotions should be dynamically swapable

## 2. `Product` Class Mutability: Mutable

- **Ease of promotion chaining:** Directly moddifying discount prince, makes promotion chaining easier, because they run on single object instance

- **Ensured data safety via deep copies:** Promotions are applied on a deep copy of original product iterable, so original iterable can't be overwriten.
