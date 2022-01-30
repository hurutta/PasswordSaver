<template>
    <div>
        


        <div class="newadd">
        <input type="text" v-model="value" placeholder="Website URL" />
        <input type="text" v-model="value2" placeholder="Password" />
        <button v-on:click="addItem(value,value2);fetchItems();">ADD NOW</button>
 
        </div>

        <table>
            <thead>
            <tr>
                <td>No</td>
                <td>Website</td>
                
                <div style="width:200px">
                <td>Password <button v-on:click="visibility();">Toggle</button></td> 
                </div>


                <td>Copy Password</td>
                <td>Update</td>
                <td>Remove</td>
            </tr>
            </thead>

            <tbody>
                <tr v-for="(item, index) in items" :key="item._id">
                    <td>    {{ index+1 }} </td>
                    <td>    {{ item.websiteUrl }}  </td>
                    
                    <td>    <div style="overflow: hidden;width: 150px;" id="myDIV" v-if="vsblt">
                            {{ item.websitePassword }}    </div></td>
                    
                    <td>    <button v-on:click="copyPassword(item.websitePassword)">Copy Password</button>   </td>
                    <td>    <button v-on:click="updatePassword(item);" class="bt2">Update</button>    </td>
                    <td>    <button class="bt3"  v-on:click="removeWebsite(item.websiteUrl,item.websitePassword,index)"> Remove </button>    </td>
                </tr>
            </tbody>
        </table>

    </div>
</template>

<script>

    export default {
        data(){
            return{
                value: '',
                value2: '',
                items: [],
                vsblt:true
            }
        },
        components: {
      name: 'AddItem'
  },

        created: function()
        {
            this.fetchItems();
        },

        methods: 
        {
            fetchItems()
            {


                let uri = 'http://localhost:8888/all';
                var body = {username: this.$parent.infoAccount.username, password: this.$parent.infoAccount.password, userData: [{}] };
                
                this.axios.post(uri,body).then((response) => {
                    this.items = response.data;
                });

            },
            removeWebsite(webUrl,pass,index)
            {
                let uri = 'http://localhost:8888/remove';
                


                this.axios.delete(uri, { data: {username: this.$parent.infoAccount.username, password: this.$parent.infoAccount.password, userData: [ { websiteUrl: webUrl , websitePassword: pass } ] } } ).then(response => console.log(response.data));
                this.items.splice(index, 1);
                
            },

            async copyPassword(password) 
            {

                await navigator.clipboard.writeText(password);
                alert("Password is Copied: " + password);
            },
            updatePassword(item)
            {
                let uri = 'http://localhost:8888/update';
                var newPass = window.prompt("Enter your new Password for " + item.websiteUrl + " : ");
                var body = {username: this.$parent.infoAccount.username, password: this.$parent.infoAccount.password, userData: [ { websiteUrl: item.websiteUrl , websitePassword: newPass } ] };

                this.axios.put(uri, body).then(response => { 
                        console.log(response.data); 
                        item.wesitePassword = newPass;
                        this.fetchItems();
                });
            },
            addItem(webUrl,pass) 
            {
                let uri = 'http://localhost:8888/create';
                var body = {username: this.$parent.infoAccount.username, password: this.$parent.infoAccount.password, userData: [ { websiteUrl: webUrl , websitePassword: pass } ] };

                    this.axios.post(uri, body).then(response => { 
                        console.log(response.data);
                        
                         this.fetchItems();
                });

            },
            visibility()
            {
                if(this.vsblt == true)
                {
                    this.vsblt = false;                
                }else
                {
                    this.vsblt = true;
                }
            }
        }
    }
    
</script>

<style>

    @import '../assets/SecureStyle.css';

</style>
