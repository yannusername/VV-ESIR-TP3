package fr.istic.vv;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if (isValidDate(day, month, year)){
            this.day=day;
            this.month=month;
            this.year=year;
        }
        else{
            throw new IllegalArgumentException("Format de date invalide");
        }


    }

    public static boolean isValidDate(int day, int month, int year) {
        if (day<1 || day>31 ){ // Cas Jour, Mois ou Année négative + Mois inférieur à 13 + Jour supérieur à 31
            return false;
        }
        if (month<1 || month > 12){
            return false;
        }
        int[] month_30 = {4, 6, 9, 11};
        for (int elem : month_30){
            if (elem==month){ // 30 Jours dans le Mois de Avril, Juin, Septembre, Novembre
                if (day>30){
                    return false;
                }
            }
        }

        if (month==2){ // Cas du mois de février en prenant en compte les années bisextilles 
            if (year%4 ==0){
                if (day>29){
                    return false;
                }
            }
            else{
                if (day>28){
                    return false;
                }
            } 
        }

        return true;
    }

    public static boolean isLeapYear(int year) { return year%4==0;}

    public Date nextDate() {
        int[] month_30 = {4, 6, 9, 11};
        int[] month_31 = {1, 3, 5, 7, 8, 10, 12};
        for (int elem: month_30){ // POur les mois de 30 jours
            if(elem==this.month){
                if (this.day==30){
                    return new Date(1, this.month+1, this.year);
                }
                else{
                    return new Date(this.day+1, this.month, this.year);
                }
            }
        }

        for (int elem: month_31){ // POur les mois de 31 jours
            if(elem==this.month){
                if (this.day==31 && this.month==12){ // Cas du 31 Décembre
                    return new Date(1, 1, this.year+1);
                }
                else if (this.day==31){
                    return new Date(1, this.month+1, this.year);
                }
                else{
                    return new Date(this.day+1, this.month, this.year);
                }
            }
        }

            // Cas du mois de février en prenant en compte les années bisextilles 
        if (this.year%4 ==0){
            if (day==29){
                return new Date(1, this.month+1, this.year);
            }
            else{
                return new Date(this.day+1, this.month, this.year);
            }
        }

        if (day==28){
            return new Date(1, this.month+1, this.year);
        }
        
        return new Date(this.day+1, this.month, this.year);
    }

    public Date previousDate() {
        int[] month_30 = {4, 6, 9, 11};
        int[] month_31 = {1, 3, 5, 7, 8, 10, 12};

        for (int elem: month_30){ // POur les mois de 30 jours
            if(elem==this.month){
                if (this.day==1){
                    return new Date(31, this.month-1, this.year);
                }
                else{
                    return new Date(this.day-1, this.month, this.year);
                }
            }
        }

        for (int elem: month_31){ // Pour les mois de 31 jours
            if(elem==this.month){
                if (this.day==1){
                    for (int elem2: month_31){
                        if(elem2==this.month-1 || this.month==1){

                            if (this.month==1){
                                return new Date(31, 12, this.year-1);
                            }
                            else{
                                return new Date(31, this.month-1, this.year);
                            }
                        }
                    }
                    if (this.month-1==2){
                        if (this.year%4 ==0){
                            return new Date(29, this.month-1, this.year);
                        }
                        else{
                            return new Date(28, this.month-1, this.year);
                        }
                    }
                    else{
                        return new Date(30, this.month-1, this.year);
                    }
                    
                }
                else{
                    return new Date(this.day-1, this.month, this.year);
                }
            }
        }

        if (this.day==1){ //Cas du premier Février
            return new Date(31, this.month-1, this.year); 
        }
        else{ // Cas pour les autres jours de février
            return new Date(this.day-1, this.month, this.year); 
        }
    }
    @Override
    public int compareTo(Date other) {
        if (other==null){
            throw new NullPointerException("Objet null");
        }

        else if (this.year==other.year && this.month==other.month && this.day==other.day){
            return 0;
        }

        else if( this.year > other.year){
            return 1;
        }

        else if( this.year < other.year){
            return -1;
        }

        else if( this.month > other.month){
            return 1;
        }

        else if( this.month < other.month){
            return -1;
        }

        else if( this.day > other.day){
            return 1;
        }


        else{
            return -1;
        }
    }

    public int getDay(){
        return this.day;
    }

    public int getMonth(){
        return this.month;
    }

    
    public int getYear(){
        return this.year;
    }

}