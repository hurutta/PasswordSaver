<template>
    <div id="login">
        <h1>Password Saver</h1>
        <input class="loginInput" type="text" name="username" v-model="input.username" placeholder="Username" />
        <br>
        <input class="loginInput" type="password" name="password" v-model="input.password" placeholder="Password" />
        <br><br><br>
        <button class="bt1" type="button" v-on:click="login(input.username,input.password)">Login</button>
        : :
        <button class="bt1" type="button" v-on:click="register(input.username,input.password)">Register</button>
        
        <br><br><br>

        
    </div>

</template>

<script>
    export default {
        name: 'Login',
        data() {
            return {
                input: {
                    username: "",
                    password: "",
                    newUsername: "",
                    newPassword:""
                }
            }
        },
        methods: 
        {
            login(usrname,pass) 
            {

                let uri = 'https://pass-word-saver-backend.herokuapp.com/login';
                var hash = this.CryptoJS.SHA3(pass).toString();
                var query = {username: usrname, password: hash, userData: [{}] };
                this.axios.post(uri, query).then((response) => {
                        

                        if(response.data == true)
                        {
                            this.$emit("authenticated", true);
                            this.$router.replace({ name: "secure" });
                            this.$parent.infoAccount.username = usrname;
                            this.$parent.infoAccount.password = hash;
                        }else
                        {
                            console.log("The username and / or password is incorrect");
                            alert("The username and / or password is incorrect");
                        }
                   
                });
                
            },

            register(usrname,pass) 
            {

                let uri = 'https://pass-word-saver-backend.herokuapp.com/create';
                const hash = this.CryptoJS.SHA3(pass).toString();
                
                var query = {username: usrname, password: hash, userData: [{ websiteUrl: "Demo website" , websitePassword: "DemoPassword" }] };
                this.axios.post(uri, query).then(response => 
                    console.log(response.data),alert("Account Created, Login to access"));
            }

        }
    }
</script>

<style scoped>
    @import '../assets/SecureStyle.css';
</style>