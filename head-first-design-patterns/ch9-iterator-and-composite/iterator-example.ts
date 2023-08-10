interface MenuItem {
    getName(): string;

    getPrice(): number;
}

class DinerMenuIterator implements Iterator {
    private readonly menuItems: MenuItem[];
    private position: number = 0;

    constructor(menuItems: MenuItem[]) {
        this.menuItems = menuItems;
    }

    hasNext(): boolean {
        return this.menuItems.length !== 0;
    }

    next(): MenuItem {
        const menuItem: MenuItem = this.menuItems[this.position];
        this.position = this.position + 1;

        return menuItem;
    }

}

interface Iterator {
    hasNext(): boolean;

    next(): MenuItem;
}

class DinerMenu {
    static MAX_ITEMS = 6;
    numberOfItems = 0;
    menuItems: MenuItem[] = [];

    public Iterator(): DinerMenuIterator {
        return new DinerMenuIterator(this.menuItems);
    }
}

class Waitress {
    private pancakeHouseMenu: PancakeHouseMenu;
    private dinerMenu: DinerMenu;

    constructor(pancakeHouseMenu: PancakeHouseMenu, dinerMenu: DinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    printMenu() {
        const pancakeIterator = this.pancakeHouseMenu.Iterator();
        const dinerIterator = this.dinerMenu.Iterator();
        console.log("MENU\n----\nBREAKFAST");
        this.printMenuHelper(pancakeIterator);
        console.log("\nLUNCH");
        this.printMenuHelper(dinerIterator);
    }

    private printMenuHelper(iterator: Iterator) {
        while (iterator.hasNext()) {
            const menuItem: MenuItem = iterator.next();
            console.log(`menuItem.getName(): ${menuItem.getName()}`);
            console.log(`menuItem.getPrice(): ${menuItem.getPrice()}`);
        }
    }
}

class PancakeHouseIterator implements Iterator {
    hasNext(): boolean {
        return false;
    }

    next(): MenuItem {
        return undefined;
    }
}

class PancakeHouseMenu {
    Iterator() {
        return new PancakeHouseIterator();
    }
}

function main() {
    const pancakeHouseMenu = new PancakeHouseMenu();
    const dinerMenu = new DinerMenu();

    new Waitress(pancakeHouseMenu, dinerMenu).printMenu();
}

main()
